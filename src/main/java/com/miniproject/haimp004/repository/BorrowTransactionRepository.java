package com.miniproject.haimp004.repository;

import com.miniproject.haimp004.data.BorrowTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BorrowTransactionRepository extends JpaRepository<BorrowTransaction, Integer> {

    @Query(value = "SELECT * FROM borrow_transaction b WHERE b.id_user LIKE %:idUser", nativeQuery = true)
    List<BorrowTransaction> listBorrowedBookByUser(@Param("idUser") Integer idUser);

    @Query(value = "SELECT * FROM borrow_transaction b WHERE b.id_product LIKE %:idProduct", nativeQuery = true)
    List<BorrowTransaction> listWhoBorrowBook(@Param("idProduct") Integer idProduct);
}
