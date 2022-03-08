package com.miniproject.haimp004.controller;

import com.miniproject.haimp004.data.*;
import com.miniproject.haimp004.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private LiveWeatherService liveWeatherService;

    @Autowired
    private BorrowTransactionService borrowTransactionService;

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
        String email = userAuth.getUsername();
        User user = userService.getUserByEmail(email);
        model.addAttribute("message", user.getName());

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

    @RequestMapping("/borrow")
    public  String viewListBorrow(Model model){
        List<BorrowTransaction> listBorrowTransaction = borrowTransactionService.listAll();
        model.addAttribute("listBorrow", listBorrowTransaction);

        return "borrow";
    }

    @GetMapping(path = "/init")
    public String initProcess(){
        return "boom";
    }

    @GetMapping(path = "/editpassword")
    public ModelAndView editPassword(){
        ModelAndView modelAndView = new ModelAndView("edit_password");
        System.out.println("ahai");
        NewPassword newPassword = new NewPassword();
        modelAndView.addObject("newpassword", newPassword);
        return modelAndView;
    }

    @RequestMapping(value = "/editpassword/save", method = RequestMethod.POST)
    public String saveNewPassword(@ModelAttribute(name = "newpassword") NewPassword newPassword, @AuthenticationPrincipal CustomUserDetails userAuth){
        String email = userAuth.getUsername();
        User user = userService.getUserByEmail(email);
        System.out.println("ohoi");
        System.out.println(newPassword.getNewPassword());
        user.setPassword(newPassword.getNewPassword());
        userService.save(user);

        return "redirect:/homepage";
    }

    @RequestMapping("/logout")
    public String logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/";
    }
}
