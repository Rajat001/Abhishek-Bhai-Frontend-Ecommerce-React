package com.dev.repository;

import com.dev.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findByUserId(Long userId);
}
