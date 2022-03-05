package com.miniproject.haimp004.repository;

import com.miniproject.haimp004.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM user u WHERE u.userName LIKE %:keyword%", nativeQuery = true)
    List<User> findUserByKeyword(@Param("keyword") String keyword);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.name = ?1")
    public User findByName(String name);

    @Query(value = "SELECT count(*) FROM user", nativeQuery = true)
    Integer countUser();
}
