package com.miniproject.haimp004.controller;

import com.miniproject.haimp004.data.User;
import com.miniproject.haimp004.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
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
