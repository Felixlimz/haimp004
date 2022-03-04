package com.miniproject.haimp004.controller;

import com.miniproject.haimp004.data.Category;
import com.miniproject.haimp004.data.User;
import com.miniproject.haimp004.repository.UserRepository;
import com.miniproject.haimp004.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model, String error, String logout){
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = "/test")
    public String testPage(Model model){
        List<Category> listCategory = categoryService.listAll();
        model.addAttribute("listCategory", listCategory);

        Category category = new Category();
        model.addAttribute("category", category);
        return "product-category";
    }
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping(path = "/init")
//    public String initProcess(){
//        User user = new User();
//        user.setUserName("felixllim");
//        user.setPassword("password");
//        user.setEmail("felixllim@gmail.com");
//        user.setName("Felix Lim");
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//        userRepository.save(user);
//        return "SAVED";
//
//    }
}
