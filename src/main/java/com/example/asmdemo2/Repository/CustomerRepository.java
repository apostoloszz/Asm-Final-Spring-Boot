package com.example.asmdemo2.Repository;


import com.example.asmdemo2.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
    Customer findByEmailAndPassword(String email, String password);
}