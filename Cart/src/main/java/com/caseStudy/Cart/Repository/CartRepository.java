package com.caseStudy.Cart.Repository;

import com.caseStudy.Cart.model.Cart;
import com.caseStudy.Cart.model.Products;
import com.caseStudy.Cart.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Object findByUsersAndProducts(Users users, Products products);

    List<Cart> findByUsers(Users users);

    String deleteAllByUsersAndProducts(Users users, Products products);

    ArrayList<Cart> findAllByUsers(Users users);

}
