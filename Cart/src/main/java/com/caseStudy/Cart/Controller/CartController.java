package com.caseStudy.Cart.Controller;

import com.caseStudy.Cart.Service.CartService;
import com.caseStudy.Cart.Service.UserService;
import com.caseStudy.Cart.model.Cart;
import com.caseStudy.Cart.model.OrderHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @GetMapping("/addToCart/{productId}")
    public Cart addToCart(@PathVariable("productId") Long productId, Principal principal) {
        return (Cart) cartService.addProduct(userService.getUserId(principal), productId);
    }

    @GetMapping("/removeOneFromCart/{productId}")
    public Cart removeOneFromCart(@PathVariable("productId") Long productId, Principal principal) {
        return cartService.subtractProduct(userService.getUserId(principal), productId);
    }

    @GetMapping("/removeFromCart/{productId}")
    public String removeFromCart(@PathVariable("productId") Long productId, Principal principal) {
        String str = cartService.removeProduct(userService.getUserId(principal), productId);
        return "Product Removed";
    }

    @GetMapping("/showCart")
    public List<Cart> showCart(Principal principal) {
        return cartService.showUserProducts(userService.getUserId(principal));
    }
    @GetMapping("/checkout")
    public List<OrderHistory> checkOutFromCart(Principal principal) {
        return cartService.checkout(principal);

    }

}
