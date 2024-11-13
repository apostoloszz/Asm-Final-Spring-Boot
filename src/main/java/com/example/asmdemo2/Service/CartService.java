package com.example.asmdemo2.Service;



import com.example.asmdemo2.Entity.CartItem;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    void addProduct(Long productId, int quantity);
    void removeProduct(Long productId);
    void updateProductQuantity(Long productId, int quantity);
    List<CartItem> getCartItems();
    BigDecimal getTotal();
    void clearCart();
}
