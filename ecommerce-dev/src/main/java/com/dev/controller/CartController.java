package com.dev.controller;


import com.dev.exceptions.ProductException;
import com.dev.model.Cart;
import com.dev.model.CartItem;
import com.dev.model.Product;
import com.dev.model.User;
import com.dev.request.AddItemRequest;
import com.dev.response.ApiResponse;
import com.dev.service.CartItemService;
import com.dev.service.CartService;
import com.dev.service.ProductService;
import com.dev.service.UserService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")

public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Cart> findUserCartHandler(
            @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findUserCart(user);
        System.out.println("cart - " + cart.getUser().getEmail());

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }


    @PutMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(
            @RequestBody AddItemRequest req ,
            @RequestHeader("Authorization") String jwt)
            throws ProductException , Exception{

        User user = userService.findUserByJwtToken(jwt);
        Product product = productService.findProductById(req.getProductId());

        CartItem item = cartService.addCartItem(user,
                product,
                req.getSize(),
                req.getQuantity());

        ApiResponse res = new ApiResponse();
        res.setMessage("Item Added to Cart Successfully");

        return new ResponseEntity<>(item, HttpStatus.ACCEPTED);

    }


    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse res = new ApiResponse();
        res.setMessage("Item Remove from Cart");


        return new ResponseEntity<>(res , HttpStatus.ACCEPTED);
    }



    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestBody CartItem cartItem,
            @RequestHeader("Authorization") String jwt)
            throws Exception{

            User user = userService.findUserByJwtToken(jwt);

            CartItem updateCartItem = null;
            if(cartItem.getQuantity() > 0){
                updateCartItem=cartItemService.updateCartItem(user.getId(), cartItemId,cartItem);
            }

            return new ResponseEntity<>(updateCartItem , HttpStatus.ACCEPTED);
    }

}

















