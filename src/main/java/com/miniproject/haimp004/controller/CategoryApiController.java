package com.miniproject.haimp004.controller;

import com.miniproject.haimp004.data.Category;
import com.miniproject.haimp004.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/category")
public class CategoryApiController {
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewCategory (@RequestParam String name, @RequestParam String detail){
        Category newCategory = new Category();
        newCategory.setNameCategory(name);
        newCategory.setDetailCategory(detail);
        categoryRepository.save(newCategory);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
}
