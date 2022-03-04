package com.miniproject.haimp004.controller;

import com.miniproject.haimp004.data.Category;
import com.miniproject.haimp004.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/category")
public class CategoryApiController {
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @PostMapping(path = "/add")
//    public @ResponseBody String addNewCategory (@RequestParam String name, @RequestParam String detail){
//        Category newCategory = new Category();
//        newCategory.setNameCategory(name);
//        newCategory.setDetailCategory(detail);
//        categoryRepository.save(newCategory);
//        return "Saved";
//    }
//
//    @GetMapping(path = "/all")
//    public @ResponseBody Iterable<Category> getAllCategory(){
//        return categoryRepository.findAll();
//    }

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    public  String viewListCategory(Model model){
        List<Category> listCategory = categoryService.listAll();
        model.addAttribute("listCategory", listCategory);

        Category category = new Category();
        model.addAttribute("category", category);

        return "category2";
     }

    @RequestMapping("/new")
    public String viewAddNewCategory(Model model){
        Category category = new Category();
        model.addAttribute("category", category);
        return "new_category_page2";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCategoryAction(@ModelAttribute("category") Category category){
        categoryService.save(category);
        System.out.println(category);

        return "redirect:/category/list";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView viewEditCategory(@PathVariable(name = "id") int id){
        ModelAndView modelAndView = new ModelAndView("edit_category");
        Category category = categoryService.get(id);
        modelAndView.addObject("category", category);

        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    public String deleteCategoryAction(@PathVariable(name = "id") int id){
        categoryService.delete(id);
        return "redirect:/category/list";
    }
}
