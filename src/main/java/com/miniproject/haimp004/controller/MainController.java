package com.miniproject.haimp004.controller;

import com.miniproject.haimp004.data.Category;
import com.miniproject.haimp004.data.User;
import com.miniproject.haimp004.repository.UserRepository;
import com.miniproject.haimp004.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
//@RequestMapping(path = "/api")
public class MainController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model, String error, String logout){
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "index";
    }

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public String homePage(Model model, String error, String logout){
        return "homepage";
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String productPage(Model model, String error, String logout){
        return "product";
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String categoryPage(Model model, String error, String logout){
        List<Category> listCategory = categoryService.listAll();
        model.addAttribute("listCategory", listCategory);
        Category category = new Category();
        model.addAttribute("category", category);
        return "category2";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCategoryAction(@ModelAttribute("category") Category category){
        categoryService.save(category);
        System.out.println(category);

        return "redirect:/";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPage(Model model, String error, String logout){

        return "user";
    }
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/init")
    public String initProcess(){
        User user = new User();
        user.setUserName("felixlim");
        user.setPassword("password");
        user.setEmail("felixlim@gmail.com");
        user.setName("Felix Lim");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "SAVED";

    }
}
