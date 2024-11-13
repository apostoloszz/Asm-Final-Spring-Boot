package com.example.asmdemo2.Impl;


import com.example.asmdemo2.Entity.CartItem;
import com.example.asmdemo2.Entity.Product;
import com.example.asmdemo2.Service.CartService;
import com.example.asmdemo2.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@SessionScope
public class CartServiceImpl implements CartService {

    private final Map<Long, CartItem> cart = new HashMap<>();

    @Autowired
    private ProductService productService;

    @Override
    public void addProduct(Long productId, int quantity) {
        Product product = productService.findById(productId);
        if (product != null) {
            CartItem item = cart.get(productId);
            if (item == null) {
                item = new CartItem(product, quantity);
                cart.put(productId, item);
            } else {
                item.setQuantity(item.getQuantity() + quantity);
                item.setPrice(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            }
        }
    }

    @Override
    public void removeProduct(Long productId) {
        cart.remove(productId);
    }

    @Override
    public void updateProductQuantity(Long productId, int quantity) {
        CartItem item = cart.get(productId);
        if (item != null) {
            item.setQuantity(quantity);
        }
    }

    @Override
    public List<CartItem> getCartItems() {
        return new ArrayList<>(cart.values());
    }

    @Override
    public BigDecimal getTotal() {
        return cart.values().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void clearCart() {
        cart.clear();  // Clear all items from the cart
    }

}
