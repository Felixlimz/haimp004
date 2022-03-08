package com.miniproject.haimp004.controller;

import com.miniproject.haimp004.data.BorrowTransaction;
import com.miniproject.haimp004.data.Category;
import com.miniproject.haimp004.data.Product;
import com.miniproject.haimp004.service.BorrowTransactionService;
import com.miniproject.haimp004.service.CategoryService;
import com.miniproject.haimp004.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/product")
public class ProductApiController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BorrowTransactionService borrowTransactionService;

    @RequestMapping("/new")
    public String viewAddNewProduct(Model model){
        Product product = new Product();
        model.addAttribute("product", product);

        List<Category> listCategory = categoryService.listAll();
        model.addAttribute("listCategory", listCategory);

        return "new_product_page";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProductAction(@ModelAttribute("product") Product product){
        productService.saveProduct(product);

        System.out.println(product);
        System.out.println("SUKSES");

        return "redirect:/product";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView viewEditProduct(@PathVariable(name = "id") int id){
        ModelAndView modelAndView = new ModelAndView("edit_product");
        Product product = productService.get(id);
        modelAndView.addObject("product", product);

        List<Category> listCategory = categoryService.listAll();

        Category temp = new Category();
        for(Category category: listCategory){
            if(category.getNameCategory().equals(product.getProductCategory())){
                temp = category;
            }
        }
        int itemPos = listCategory.indexOf(temp);
        System.out.println(itemPos);
        System.out.println(temp);
        listCategory.remove(itemPos);
        listCategory.add(0, temp);

        modelAndView.addObject("listCategory", listCategory);

        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProductAction(@PathVariable(name = "id") int id){
        productService.deleteProduct(id);
        return "redirect:/product";
    }

    @RequestMapping("/detail/{id}")
    public ModelAndView viewProduct(@PathVariable(name = "id") int id){
        ModelAndView modelAndView = new ModelAndView("product_detail");
        Product product = productService.get(id);
        modelAndView.addObject("product", product);

        List<BorrowTransaction> listBorrow = borrowTransactionService.listWhoBorrowBook(id);
        int size = listBorrow.size();
        modelAndView.addObject("listBorrow", listBorrow);
        modelAndView.addObject("sizeBorrow", size);

        return modelAndView;
    }

}
