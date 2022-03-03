package com.miniproject.haimp004.controller;

import com.miniproject.haimp004.data.Category;
import com.miniproject.haimp004.data.Product;
import com.miniproject.haimp004.repository.CategoryRepository;
import com.miniproject.haimp004.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api/product")
public class ProductApiController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewProduct (@RequestParam String name,
                                               @RequestParam String category,
                                               @RequestParam Integer stock,
                                               @RequestParam Integer productNo){
        Optional<Category> id = categoryRepository.findById(category);
        if(id.isEmpty()) {
            return "Save Failed. No Such Category";
        }
        Product newProduct = new Product();
        newProduct.setProductNo(productNo);
        newProduct.setProductName(name);
        newProduct.setProductStock(stock);
        newProduct.setProductCategory(id.get());
        return "Saved";
    }
}
