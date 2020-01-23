package com.caseStudy.Cart.Service;

import com.caseStudy.Cart.Repository.CartRepository;
import com.caseStudy.Cart.Repository.OrderHisRepository;
import com.caseStudy.Cart.Repository.ProductsRepository;
import com.caseStudy.Cart.Repository.UsersRepository;
import com.caseStudy.Cart.model.Cart;
import com.caseStudy.Cart.model.Products;
import com.caseStudy.Cart.model.Users;
import com.caseStudy.Cart.model.OrderHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class CartService {

    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private OrderHisRepository orderHistoryRepository;

    public Object addProduct(Long user_id, Long product_id) {

        Products products = productsRepository.findByProductId(product_id);

        Users users = usersRepository.findByUserId(user_id);

        if(cartRepository.findByUsersAndProducts(users, products)!=null) {

            Cart cart = (Cart) cartRepository.findByUsersAndProducts(users, products);
            cart.setQuantity(cart.getQuantity()+1);
            cartRepository.save(cart);
        } else {
            Cart cart = new Cart(products, users, 1);
            cartRepository.save(cart);
        }

        return cartRepository.findByUsersAndProducts(users, products);
    }


    public String removeProduct(Long userId, Long productId) {
        Products products = productsRepository.findByProductId(productId);
        Users users = usersRepository.findByUserId(userId);
        cartRepository.deleteAllByUsersAndProducts(users, products);
        return "removed";
    }

    public List<Cart> showUserProducts(Long userId) {
        Users users = usersRepository.findByUserId(userId);
        return cartRepository.findByUsers(users);
    }


    public Cart subtractProduct(Long userId, Long productId) {

        Products products = productsRepository.findByProductId(productId);

        Users users = usersRepository.findByUserId(userId);

        if(cartRepository.findByUsersAndProducts(users, products)!=null) {

            Cart cart = (Cart) cartRepository.findByUsersAndProducts(users, products);
            if(cart.getQuantity()>=2) {
                cart.setQuantity(cart.getQuantity() - 1);
                cartRepository.save(cart);
            } else if(cart.getQuantity()==1) {
                removeProduct(userId, productId);
            }
        }
        return (Cart) cartRepository.findByUsersAndProducts(users, products);
    }
    public List<OrderHistory> checkout(Principal principal) {
        Users users = usersRepository.getByEmail(principal.getName());
        ArrayList<Cart> cartList = cartRepository.findAllByUsers(users);
        for(Cart cart : cartList) {
            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setUserId(cart.getUsers().getUserId());
            orderHistory.setQuantity(cart.getQuantity());
            orderHistory.setPrice(cart.getProducts().getPrice());
            orderHistory.setName(cart.getProducts().getName());
            orderHistory.setImage(cart.getProducts().getImage());
            orderHistory.setProductId(cart.getProducts().getProductId());
            orderHistory.setDate(new Date());
            cartRepository.delete(cart);
            orderHistoryRepository.saveAndFlush(orderHistory);
        }
        return orderHistoryRepository.findAllByUserId(users.getUserId());
    }
}
