package com.miniproject.haimp004.repository;

import com.miniproject.haimp004.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM product p WHERE p.productName LIKE %:keyword%", nativeQuery = true)
    List<Product> findProductByKeyword(@Param("keyword") String keyword);

    @Query(value = "SELECT count(*) FROM product", nativeQuery = true)
    Integer countProduct();

    @Query(value = "SELECT * FROM product p WHERE p.product_category = :categoryName", nativeQuery = true)
    List<Product> listProductByCategory(@Param("categoryName") String categoryName);

}
