package com.miniproject.haimp004.controller;

import com.miniproject.haimp004.data.Category;
import com.miniproject.haimp004.data.Product;
import com.miniproject.haimp004.data.User;
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
public class CategoryApiController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;


//    @RequestMapping("/list")
//    public  String viewListCategory(Model model){
//        List<Category> listCategory = categoryService.listAll();
//        model.addAttribute("listCategory", listCategory);
//
//        Category category = new Category();
//        model.addAttribute("category", category);
//
//        return "category";
//     }

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

    @RequestMapping("")
    public String viewListCategoryPagination(Model model, @RequestParam int pages){
        Page<Category> listCategory = categoryService.listAllPaging(pages, 5);
        model.addAttribute("listCategory", listCategory);

        return "list_category_page";
    }

    @RequestMapping("/{id}")
    public String viewProductByCategory(Model model, @PathVariable int id){
        List<Product> listProduct = productService.listProductByCategory(categoryService.get(id).getNameCategory());
        model.addAttribute("listProduct", listProduct);
        return "product_category";
    }

    @RequestMapping("/test")
    public  String viewListCategoryPagination(Model model, @RequestParam(required = false) Integer page){
        if(page == null){
            page = 0;
        }
        Page<Category> listCategory = categoryService.listAllPaging(page, 5);
        model.addAttribute("listCategory", listCategory);

        return "list_category_page";
    }

}
