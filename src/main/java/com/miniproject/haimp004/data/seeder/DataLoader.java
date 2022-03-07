package com.miniproject.haimp004.data.seeder;

import com.miniproject.haimp004.data.Category;
import com.miniproject.haimp004.data.Product;
import com.miniproject.haimp004.data.User;
import com.miniproject.haimp004.service.CategoryService;
import com.miniproject.haimp004.service.ProductService;
import com.miniproject.haimp004.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @EventListener
    private void run(ContextRefreshedEvent event){
        loadData();
    }

    private void loadData() {
        if(userService.countUser() == 0){
            User user = new User();
            user.setUserName("felixlim");
            user.setPassword("password");
            user.setEmail("felix@gmail.com");
            user.setName("Felix Lim");
            userService.save(user);
        }

        if(categoryService.countCategory() == 0){
            Category category = new Category();
            category.setNameCategory("Folklore of Indonesia");
            category.setDetailCategory("Indonesian folklore is a story that comes from the Indonesian people which has been passed down orally.");
            categoryService.save(category);

            Category category2 = new Category();
            category2.setNameCategory("Horror");
            category2.setDetailCategory("Painful and intense fear, dread, or dismay.");
            categoryService.save(category2);
        }

        if(productService.countProduct() == 0){
            Product product = new Product();
            product.setProductName("Malin Kundang");
            product.setProductCategory("Folklore of Indonesia");
            product.setProductStock(3);
            product.setImageLink("https://ebooks.gramedia.com/ebook-covers/12492/big_covers/ID_HHK2014MTH05MKUN_B.jpg");
            productService.saveProduct(product);

            Product product2 = new Product();
            product2.setProductName("House of Leaves");
            product2.setProductCategory("Horror");
            product2.setProductStock(2);
            product2.setImageLink("https://images-na.ssl-images-amazon.com/images/I/810pcXb+l3L.jpg");
            productService.saveProduct(product2);
        }
    }
}
