package com.example.asmdemo2.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    private BigDecimal total;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    public void calculateTotal() {
        this.total = orderDetails.stream()
                .map(orderDetail -> orderDetail.getPrice().multiply(new BigDecimal(orderDetail.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}