package com.example.asmdemo2.Service;



import com.example.asmdemo2.Entity.CartItem;
import com.example.asmdemo2.Entity.Customer;
import com.example.asmdemo2.Entity.Order;

import java.util.List;
public interface OrderService {
    Order createOrder(Customer customer, List<CartItem> cartItems);
    List<Order> findOrdersByCustomer(Long customerId);
    Order findOrderById(Long orderId);
    // Add this method to the interface
    void saveOrder(Order order);
}


