package com.dev.service;

import com.dev.model.Cart;
import com.dev.model.Coupon;
import com.dev.model.User;

import java.util.List;

public interface CouponService {

    Cart applyCoupon(String code , double orderValue, User user) throws Exception;
    Cart removeCoupon(String code, User user) throws Exception;
    Coupon findCouponById(Long id) throws Exception;
    Coupon createCoupon(Coupon coupon);
    List<Coupon> findAllCoupons();
    void deleteCoupon(Long id) throws Exception;
}
