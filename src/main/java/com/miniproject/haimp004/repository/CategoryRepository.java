package com.miniproject.haimp004.repository;

import com.miniproject.haimp004.data.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT * FROM category c WHERE c.nameCategory LIKE %:keyword%", nativeQuery = true)
    List<Category> findCategoryByKeyword(@Param("keyword") String keyword);
}
