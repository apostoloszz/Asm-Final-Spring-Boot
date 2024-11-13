package com.example.asmdemo2.Controller;

import com.example.asmdemo2.Entity.Customer;
import com.example.asmdemo2.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/profile")
    public String viewProfile(Model model, @RequestParam("customerId") Long customerId) {
        Customer customer = customerService.findById(customerId);
        model.addAttribute("customer", customer);
        return "account/profile";
    }

    @GetMapping("/edit/{id}")
    public String showEditProfileForm(@PathVariable("id") Long customerId, Model model) {
        Customer customer = customerService.findById(customerId);
        model.addAttribute("customer", customer);
        return "account/edit";
    }

    @PostMapping("/update")
    public String updateProfile(@ModelAttribute("customer") Customer customer) {
        customerService.updateCustomer(customer);
        return "redirect:/account/profile?customerId=" + customer.getId();
    }
}
