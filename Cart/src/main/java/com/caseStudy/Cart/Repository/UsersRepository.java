package com.caseStudy.Cart.Repository;

import com.caseStudy.Cart.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

    Users findByUserId(Long user_id);

    Users getByEmail(String email);
}
