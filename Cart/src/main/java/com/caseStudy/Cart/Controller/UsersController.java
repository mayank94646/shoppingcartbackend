package com.caseStudy.Cart.Controller;

import com.caseStudy.Cart.Service.UserService;
import com.caseStudy.Cart.model.Users;
import com.caseStudy.Cart.model.OrderHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
public class UsersController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/validateLogin", produces = "application/json")
    public String validateLogin() { return "\"valid\""; }

    @PostMapping(value = "/addUsers")
    public Users addUser(@RequestBody Users user) { return userService.addUser(user); }

    @GetMapping("/callUser")
    public Users callUsers(Principal principal)
    {
        return userService.callUser(principal);
    }

    @PostMapping("/editUser")
    public Users editUsers(@RequestBody Users user)
    {
        return userService.changeUserDetails(user);
    }
}
