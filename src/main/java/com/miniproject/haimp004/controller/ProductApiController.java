package com.miniproject.haimp004.controller;

import com.miniproject.haimp004.data.Category;
import com.miniproject.haimp004.data.Product;
import com.miniproject.haimp004.repository.CategoryRepository;
import com.miniproject.haimp004.repository.ProductRepository;
import com.miniproject.haimp004.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/product")
public class ProductApiController {
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @PostMapping(path = "/add")
//    public @ResponseBody String addNewProduct (@RequestParam String name,
//                                               @RequestParam String category,
//                                               @RequestParam Integer stock,
//                                               @RequestParam Integer productNo){
//        Optional<Category> id = categoryRepository.findById(category);
//        if(id.isEmpty()) {
//            return "Save Failed. No Such Category";
//        }
//        Product newProduct = new Product();
//        newProduct.setProductNo(productNo);
//        newProduct.setProductName(name);
//        newProduct.setProductStock(stock);
//        newProduct.setProductCategory(id.get());
//        productRepository.save(newProduct);
//        return "Saved";
//    }
//
//    @GetMapping("/all")
//    public @ResponseBody Iterable<Product> getAllProduct(){
//        return productRepository.findAll();
//    }


    @Autowired
    private ProductService productService;

    @RequestMapping("/list")
    public String viewListProduct(Model model){
        List<Product> listProduct = productService.listAll();
        model.addAttribute("listProducts", listProduct);

        return "list_product_page";
    }

    @RequestMapping("/new")
    public String viewAddNewProduct(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product_page";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProductAction(@ModelAttribute("product") Product product){
        productService.saveProduct(product);

        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView viewEditProduct(@PathVariable(name = "id") int id){
        ModelAndView modelAndView = new ModelAndView();
        Product product = productService.get(id);
        modelAndView.addObject("product", product);

        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProductAction(@PathVariable(name = "id") int id){
        productService.deleteProduct(id);
        return "redirect:/";
    }

}
