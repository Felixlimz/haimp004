package com.miniproject.haimp004.controller;

import com.miniproject.haimp004.data.Category;
import com.miniproject.haimp004.data.CustomUserDetails;
import com.miniproject.haimp004.data.Product;
import com.miniproject.haimp004.data.User;
import com.miniproject.haimp004.repository.UserRepository;
import com.miniproject.haimp004.service.CategoryService;
import com.miniproject.haimp004.service.LiveWeatherService;
import com.miniproject.haimp004.service.ProductService;
import com.miniproject.haimp004.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping(path = "/api")
public class MainController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private LiveWeatherService liveWeatherService;

    public MainController(LiveWeatherService liveWeatherService) {
        this.liveWeatherService = liveWeatherService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model, String error, String logout){
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "index";
    }

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public String homePage(Model model, @AuthenticationPrincipal CustomUserDetails userAuth){
        model.addAttribute("message", userAuth.getUsername());

        model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather("Jakarta", "id"));
        model.addAttribute("countUser", userService.countUser());
        model.addAttribute("countProduct", productService.countProduct());
        model.addAttribute("countCategory", categoryService.countCategory());
        return "homepage";
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String productPage(Model model, String error, String logout){
        List<Product> listProduct = productService.listAll();
        model.addAttribute("listProducts", listProduct);

        return "product";
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String categoryPage(Model model, String error, String logout){
        List<Category> listCategory = categoryService.listAll();
        model.addAttribute("listCategory", listCategory);
        Category category = new Category();
        model.addAttribute("category", category);
        return "category";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPage(Model model, String error, String logout){
        List<User> listUser = userService.listAll();
        model.addAttribute("listUser", listUser);

        User user = new User();
        model.addAttribute("user", user);

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
        userRepository.save(user);
        return "SAVED";

    }
}
