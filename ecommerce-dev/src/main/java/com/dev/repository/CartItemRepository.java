package com.dev.repository;

import com.dev.model.Cart;
import com.dev.model.CartItem;
import com.dev.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}
