package com.miniproject.haimp004.repository;

import com.miniproject.haimp004.data.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT * FROM category c WHERE c.name_category = :name", nativeQuery = true)
    Category findCategoryByName(@Param("name") String name);

    @Query(value = "SELECT count(*) FROM category", nativeQuery = true)
    Integer countCategory();
}
