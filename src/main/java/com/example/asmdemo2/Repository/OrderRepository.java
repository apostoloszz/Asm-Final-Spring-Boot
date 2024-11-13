package com.example.asmdemo2.Repository;


import com.example.asmdemo2.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
    Order findByIdAndCustomerId(Long orderId, Long customerId);

}