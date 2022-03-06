package com.miniproject.haimp004.controller;


import com.miniproject.haimp004.data.BorrowTransaction;
import com.miniproject.haimp004.data.Category;
import com.miniproject.haimp004.data.Product;
import com.miniproject.haimp004.data.User;
import com.miniproject.haimp004.service.BorrowTransactionService;
import com.miniproject.haimp004.service.ProductService;
import com.miniproject.haimp004.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.List;

@RequestMapping(path = "/borrow")
public class BorrowController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BorrowTransactionService borrowTransactionService;

    @RequestMapping(path = "/add/{productid}")
    public ModelAndView viewAddBorrow(@PathVariable(name = "productid") int productId){
        ModelAndView modelAndView = new ModelAndView("request_borrow");
        Product product = productService.get(productId);
        modelAndView.addObject("product", product);

        List<User> listUser = userService.listAll();
        modelAndView.addObject("listUser", listUser);
        return modelAndView;
    }

    @RequestMapping(value = "/add/{productid}/{userid}", method = RequestMethod.POST)
    public String saveBorrow(@PathVariable(name = "productid") int productId, @PathVariable(name = "userid") int userId){
        User user = userService.get(userId);
        Product product = productService.get(productId);

        BorrowTransaction borrowTransaction = new BorrowTransaction();
        borrowTransaction.setBookName(product.getProductName());
        borrowTransaction.setIdProduct(productId);
        borrowTransaction.setIdUser(userId);
        borrowTransaction.setUserName(user.getUserName());
        borrowTransaction.setUserEmail(user.getEmail());
        borrowTransaction.setBorrowDate(Calendar.getInstance().getTime());
        borrowTransactionService.save(borrowTransaction);
        return "redirect:/product";
    }

}
