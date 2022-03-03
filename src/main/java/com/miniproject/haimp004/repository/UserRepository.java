package com.miniproject.haimp004.repository;

import com.miniproject.haimp004.data.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM user u WHERE u.userName LIKE %:keyword%", nativeQuery = true)
    List<User> findUserByKeyword(@Param("keyword") String keyword);
}
