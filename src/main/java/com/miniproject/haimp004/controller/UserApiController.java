package com.miniproject.haimp004.controller;

import com.miniproject.haimp004.data.BorrowTransaction;
import com.miniproject.haimp004.data.Product;
import com.miniproject.haimp004.data.User;
import com.miniproject.haimp004.service.BorrowTransactionService;
import com.miniproject.haimp004.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping(path = "/user")
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private BorrowTransactionService borrowTransactionService;


    @GetMapping("/list")
    List<User> all() {
        return userService.listAll();
    }

    @RequestMapping("/new")
    public ModelAndView viewAddNewCategory(){
        ModelAndView modelAndView = new ModelAndView("new_user_page");
        User user = new User();
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUserAction(@ModelAttribute("user") User user){
        String defaultPassword = "12345678";
        if(userService.getUserByName(user.getName()) != null){
            defaultPassword = userService.getUserByName(user.getName()).getPassword();
        }
        user.setPassword(defaultPassword);
        user.setRoles("USER");
        userService.save(user);

        return "redirect:/user";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView viewEditUser(@PathVariable(name = "id") int id){
        ModelAndView modelAndView = new ModelAndView("edit_user");
        User user = userService.get(id);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    public String deleteUserAction(@PathVariable(name = "id") int id){
        userService.delete(id);
        return "redirect:/user";
    }

    @RequestMapping("/detail/{id}")
    public ModelAndView viewUser(@PathVariable(name = "id") int id){
        ModelAndView modelAndView = new ModelAndView("user_detail");

        List<BorrowTransaction> listBorrow = borrowTransactionService.listBorrowBookByUser(id);
        modelAndView.addObject("listBorrow", listBorrow);

        return modelAndView;
    }

}
