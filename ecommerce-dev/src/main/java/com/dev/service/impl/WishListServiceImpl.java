package com.dev.service.impl;

import com.dev.model.Product;
import com.dev.model.User;
import com.dev.model.Wishlist;
import com.dev.repository.WishListRepository;
import com.dev.service.UserService;
import com.dev.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class WishListServiceImpl implements WishListService {

    private final WishListRepository wishListRepository;
    private final UserService userService;

    @Override
    public Wishlist createWishList(User user) {
        Wishlist wishList = new Wishlist();
        wishList.setUser(user);
        return wishListRepository.save(wishList);
    }

    @Override
    public Wishlist getWithlistByUserId(User user) {
        Wishlist wishlist = wishListRepository.findByUserId(user.getId());
        if(wishlist==null){
            wishlist=createWishList(user);
        }
        return wishlist;
    }

    @Override
    public Wishlist addProductToWishlist(User user, Product product) {
        Wishlist wishlist =getWithlistByUserId(user);

        if(wishlist.getProducts().contains(product)){
            wishlist.getProducts().contains(product);
        }else{
            wishlist.getProducts().add(product);
        }
        return wishListRepository.save(wishlist);
    }
}









