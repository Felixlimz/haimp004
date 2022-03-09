package com.miniproject.haimp004.controller;

import com.miniproject.haimp004.data.Category;
import com.miniproject.haimp004.data.Product;
import com.miniproject.haimp004.service.CategoryService;
import com.miniproject.haimp004.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/new")
    public ModelAndView viewAddNewCategory(){
        ModelAndView modelAndView = new ModelAndView("new_category_page");
        Category category = new Category();
        modelAndView.addObject("category", category);

        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCategoryAction(@ModelAttribute("category") Category category){
        categoryService.save(category);
        System.out.println(category);

        return "redirect:/category";
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
        return "redirect:/category";
    }


    @RequestMapping("/{id}")
    public String viewProductByCategory(Model model, @PathVariable int id, @RequestParam(required = false) Integer page){
        if(page == null){
            page = 0;
        }
        String nameCat = categoryService.get(id).getNameCategory();
        Page<Product> pageProduct = productService.listProductByCategory(nameCat, page, 5);
        int totalPages = pageProduct.getTotalPages();
        long totalElements = pageProduct.getTotalElements();
        List<Product> listProduct = pageProduct.getContent();

        model.addAttribute("listProducts", listProduct);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("currentPage", (page + 1));
        return "category_detail";
    }

}
