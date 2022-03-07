package com.miniproject.haimp004.service;

import com.miniproject.haimp004.data.BorrowTransaction;
import com.miniproject.haimp004.repository.BorrowTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BorrowTransactionService {
    @Autowired
    private BorrowTransactionRepository borrowTransactionRepository;

    public List<BorrowTransaction> listAll(){
        return borrowTransactionRepository.findAll();
    }

    public void save(BorrowTransaction borrowTransaction){
        borrowTransactionRepository.save(borrowTransaction);
    }

    public BorrowTransaction get(Integer id){
        return borrowTransactionRepository.findById(id).get();
    }

    public void delete(Integer id){
        borrowTransactionRepository.deleteById(id);
    }

    public List<BorrowTransaction> listBorrowBookByUser(Integer idUser){
        return borrowTransactionRepository.listBorrowedBookByUser(idUser);
    }

    public List<BorrowTransaction> listWhoBorrowBook(Integer idBook){
        return borrowTransactionRepository.listWhoBorrowBook(idBook);
    }

    public Page<BorrowTransaction> listAllPaging(int page, int size){
        return borrowTransactionRepository.findAll(PageRequest.of(page, size));
    }
}
