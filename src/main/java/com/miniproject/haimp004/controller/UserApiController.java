package com.miniproject.haimp004.controller;

import com.miniproject.haimp004.data.CustomUserDetails;
import com.miniproject.haimp004.data.User;
import com.miniproject.haimp004.service.CustomUserDetailService;
import com.miniproject.haimp004.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @RequestMapping("/list")
    public  String viewListUser(Model model, @AuthenticationPrincipal CustomUserDetails userAuth){
        List<User> listUser = userService.listAll();
        model.addAttribute("listUser", listUser);

        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("message", userAuth.getUsername());

        return "list_user_page";
    }

    @RequestMapping("/new")
    public ModelAndView viewAddNewCategory(Model model){
        ModelAndView modelAndView = new ModelAndView("new_user_page");
        User user = new User();
        model.addAttribute("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUserAction(@ModelAttribute("user") User user){
        String defaultPassword = "12345678";
        if(userService.getUserByName(user.getName()) != null){
            defaultPassword = userService.getUserByName(user.getName()).getPassword();
        }
        System.out.println(defaultPassword);
        user.setPassword(defaultPassword);
        userService.save(user);
        System.out.println(user);

        return "redirect:/user/list";
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
        return "redirect:/user/list";
    }
}
