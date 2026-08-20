package com.dev.service;

import com.dev.model.Product;
import com.dev.model.User;
import com.dev.model.Wishlist;

public interface WishListService {

    Wishlist createWishList(User user);
    Wishlist getWithlistByUserId(User user);
    Wishlist addProductToWishlist(User user , Product product);
}
