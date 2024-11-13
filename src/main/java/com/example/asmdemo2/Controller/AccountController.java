package com.example.asmdemo2.Controller;

import com.example.asmdemo2.Entity.Customer;
import com.example.asmdemo2.Impl.CustomerServiceImpl;
import com.example.asmdemo2.Repository.CustomerRepository;
import com.example.asmdemo2.Service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    // Hiển thị trang đăng ký
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam(required = false) Long id,
                           @RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String password,
                           Model model) {
        try {
            Customer customer;
            if (id != null) {
                customer = customerService.findById(id);
                if (customer == null) {
                    model.addAttribute("error", "Customer not found.");
                    return "register";
                }
            } else {
                customer = new Customer();
                customer.setEmail(email);
                customer.setPassword(password);
            }
            customer.setName(name);
            customerService.register(customer);

            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "register";
        }
    }


    // Hiển thị trang đăng nhập
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Xử lý đăng nhập
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null && customer.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", customer);
            return "redirect:/products";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    // Xử lý đăng xuất
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/products";
    }
}
