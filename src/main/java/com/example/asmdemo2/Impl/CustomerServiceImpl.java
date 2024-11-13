package com.example.asmdemo2.Impl;

import com.example.asmdemo2.Entity.Customer;
import com.example.asmdemo2.Repository.CustomerRepository;
import com.example.asmdemo2.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void register(Customer customer) {
        customer.setPassword(encryptPassword(customer.getPassword()));
        customerRepository.save(customer);
    }

    @Override
    public Customer login(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null && checkPassword(password, customer.getPassword())) {
            return customer;
        }
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void changePassword(Long customerId, String newPassword) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        customer.setPassword(encryptPassword(newPassword));
        customerRepository.save(customer);
    }

    @Override
    public void resetPassword(String email) {
        // Logic to send a password reset email
    }


    @Override
    public Customer findById(Long customerId) {
        return customerRepository.findById(customerId).orElse(null); // Returns null if not found
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    private String encryptPassword(String password) {
        // Logic to encrypt the password
        return password; // Placeholder
    }

    private boolean checkPassword(String rawPassword, String encryptedPassword) {
        // Logic to check the password
        return rawPassword.equals(encryptedPassword); // Placeholder
    }
}
