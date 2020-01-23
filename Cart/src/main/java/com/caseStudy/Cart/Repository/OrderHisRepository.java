package com.caseStudy.Cart.Repository;

import com.caseStudy.Cart.model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderHisRepository extends JpaRepository<OrderHistory,Long> {
    List<OrderHistory> findAllByUserId(Long id);
}
