package com.example.asmdemo2.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    private BigDecimal price;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        // Calculate the price based on product price and quantity
        this.price = product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }


//    public CartItem(Long productId, int quantity) {
//        Product product = new Product(); // You may need to load the product from a database or another data source
//        product.setId(productId);
//        this.product = product;
//        this.quantity = quantity;
//        // You may want to calculate the price based on the product and quantity
//        this.price = BigDecimal.ZERO;
//    }

}