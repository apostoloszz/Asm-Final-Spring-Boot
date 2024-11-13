package com.example.asmdemo2.Impl;

import com.example.asmdemo2.Entity.CartItem;
import com.example.asmdemo2.Entity.Customer;
import com.example.asmdemo2.Entity.Order;
import com.example.asmdemo2.Entity.OrderDetail;
import com.example.asmdemo2.Repository.OrderRepository;
import com.example.asmdemo2.Repository.ProductRepository;
import com.example.asmdemo2.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Order createOrder(Customer customer, List<CartItem> cartItems) {
        Order order = new Order();
        order.setCustomer(customer);  // Now the customer is a managed entity
        order.setOrderDate(new Date());

        // Map cart items to order details
        List<OrderDetail> orderDetails = cartItems.stream()
                .map(cartItem -> {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setProduct(productRepository.findById(cartItem.getProduct().getId()).orElseThrow(() -> new IllegalArgumentException("Product not found")));
                    orderDetail.setQuantity(cartItem.getQuantity());
                    orderDetail.setPrice(cartItem.getPrice());
                    orderDetail.setOrder(order); // Associate the order detail with the order
                    return orderDetail;
                })
                .collect(Collectors.toList());

        order.setOrderDetails(orderDetails);

        // Calculate the total from order details
        BigDecimal total = orderDetails.stream()
                .map(od -> od.getPrice().multiply(new BigDecimal(od.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotal(total); // Set the total for the order

        return orderRepository.save(order);  // Save the order to the database
    }

    @Override
    public List<Order> findOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order); // Save the order to the database
    }
}
