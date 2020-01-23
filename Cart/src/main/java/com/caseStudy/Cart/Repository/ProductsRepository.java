package com.caseStudy.Cart.Repository;
import com.caseStudy.Cart.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

    List<Products> findAllByCategory(String category);

    List<Products> findAllByCategoryAndPriceBetween(String category, Double price1, Double price2);

    List<Products> findAllByPriceBetween(Double price1, Double price2);

    Products findByProductId(Long product_id);
}
