package com.example.asmdemo2.Service;
import com.example.asmdemo2.Entity.Customer;

public interface CustomerService {
    void register(Customer customer);
    Customer login(String email, String password);
    void updateCustomer(Customer customer);
    void changePassword(Long customerId, String newPassword);
    void resetPassword(String email);
    Customer findByEmail(String email);
    // New method to find a customer by ID
    Customer findById(Long customerId);
}
