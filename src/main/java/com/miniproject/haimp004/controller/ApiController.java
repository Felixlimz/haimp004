package com.miniproject.haimp004.controller;

import com.miniproject.haimp004.data.BorrowTransaction;
import com.miniproject.haimp004.data.Category;
import com.miniproject.haimp004.data.Product;
import com.miniproject.haimp004.data.User;
import com.miniproject.haimp004.service.BorrowTransactionService;
import com.miniproject.haimp004.service.CategoryService;
import com.miniproject.haimp004.service.ProductService;
import com.miniproject.haimp004.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BorrowTransactionService borrowTransactionService;

    @RequestMapping("/user")
    public List<User> apiListUser(){
        return userService.listAll();
    }

    @RequestMapping("/product")
    public List<Product> apiListProduct(){
        return productService.listAll();
    }

    @RequestMapping("/category")
    public List<Category> apiListCategory(){
        return categoryService.listAll();
    }

    @RequestMapping("/borrow")
    public List<BorrowTransaction> apiListBorrow(){
        return borrowTransactionService.listAll();
    }
}
