package com.example.asmdemo2.Repository;


import com.example.asmdemo2.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByShoppingCartId(Long shoppingCartId);
}