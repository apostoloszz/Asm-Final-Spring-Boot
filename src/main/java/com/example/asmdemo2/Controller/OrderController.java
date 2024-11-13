package com.example.asmdemo2.Controller;

import com.example.asmdemo2.Entity.CartItem;
import com.example.asmdemo2.Entity.Customer;
import com.example.asmdemo2.Entity.Order;
import com.example.asmdemo2.Entity.OrderDetail;
import com.example.asmdemo2.Service.CartService;
import com.example.asmdemo2.Service.CustomerService;
import com.example.asmdemo2.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JavaMailSender mailSender;

    //Hiển thị form checkout
    @GetMapping("/checkout")
    public String checkout(Model model) {
        List<CartItem> cartItems = cartService.getCartItems();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", cartService.getTotal());
        return "checkout";
    }

    @PostMapping("/checkout/confirm")
    public String confirmCheckout(Customer customer, Model model) {
        List<CartItem> cartItems = cartService.getCartItems();
        if (cartItems.isEmpty()) {
            model.addAttribute("message", "Your cart is empty.");
            return "redirect:/cart";
        }
        Customer existingCustomer = customerService.findById(customer.getId());
        if (existingCustomer == null) {
            customerService.register(customer);
            existingCustomer = customerService.findByEmail(customer.getEmail());
        } else {
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setPhone(customer.getPhone());
            customerService.updateCustomer(existingCustomer);
        }

        Order order = orderService.createOrder(existingCustomer, cartItems);
        if (order != null) {
            order.setCustomer(existingCustomer);
            order.setOrderDate(new java.util.Date());
            order.setTotal(cartService.getTotal());

            orderService.saveOrder(order);

            sendInvoiceEmail(existingCustomer.getEmail(), order);

            model.addAttribute("order", order);
            model.addAttribute("message", "Order has been successfully placed and an invoice has been sent to your email!");
            return "order-confirmation";
        }

        model.addAttribute("message", "Failed to place the order.");
        return "checkout";
    }


    private void sendInvoiceEmail(String recipientEmail, Order order) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Invoice for Your Order");
        message.setText("Thank you for your order. Here are the details:\n\n" + generateOrderSummary(order));
        mailSender.send(message);
    }

    private String generateOrderSummary(Order order) {
        StringBuilder summary = new StringBuilder();
        for (OrderDetail detail : order.getOrderDetails()) {
            summary.append("Product: ").append(detail.getProduct().getName())
                    .append(", Quantity: ").append(detail.getQuantity())
                    .append(", Price: ").append(detail.getPrice())
                    .append("\n");
        }
        summary.append("\nTotal: ").append(order.getTotal());
        return summary.toString();
    }
}
