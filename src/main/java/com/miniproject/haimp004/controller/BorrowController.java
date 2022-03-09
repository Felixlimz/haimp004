package com.miniproject.haimp004.controller;


import com.miniproject.haimp004.data.*;
import com.miniproject.haimp004.service.BorrowTransactionService;
import com.miniproject.haimp004.service.ProductService;
import com.miniproject.haimp004.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.List;


@Controller
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

    @RequestMapping(value = "/add/{productid}/{userid}")
    public String saveBorrow(@PathVariable(name = "productid") int productId, @PathVariable(name = "userid") int userId){
        User user = userService.get(userId);
        Product product = productService.get(productId);
        Integer stock = product.getProductStock() - 1;
        product.setProductStock(stock);

        BorrowTransaction borrowTransaction = new BorrowTransaction();
        borrowTransaction.setBookName(product.getProductName());
        borrowTransaction.setIdProduct(productId);
        borrowTransaction.setIdUser(userId);
        borrowTransaction.setUserName(user.getUserName());
        borrowTransaction.setUserEmail(user.getEmail());
        borrowTransaction.setBorrowDate(Calendar.getInstance().getTime());
        borrowTransactionService.save(borrowTransaction);
        productService.saveProduct(product);
        return "redirect:/borrow";
    }

    @RequestMapping("/delete/{id}")
    public String deleteBorrow(@PathVariable(name = "id") int id){
        BorrowTransaction borrowTransaction = borrowTransactionService.get(id);
        Integer idProduct = borrowTransaction.getIdProduct();
        Product product = productService.get(idProduct);
        Integer stock = product.getProductStock() + 1;
        product.setProductStock(stock);
        borrowTransactionService.delete(id);
        productService.saveProduct(product);
        return "redirect:/borrow";
    }

    @RequestMapping("/{page}/{size}")
    public  String viewListBorrowPagination(Model model, @PathVariable int page){
        Page<BorrowTransaction> listBorrowTransaction = borrowTransactionService.listAllPaging(page, 5);
        model.addAttribute("listBorrow", listBorrowTransaction);

        return "borrow";
    }

}
