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
            user.setRoles("ADMIN");
            userService.save(user);

            User user2 = new User();
            user2.setUserName("lala");
            user2.setPassword("lalalala");
            user2.setEmail("lala@gmail.com");
            user2.setName("Lala");
            user2.setRoles("USER");
            userService.save(user2);
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

            Category category3 = new Category();
            category3.setNameCategory("Classic");
            category3.setDetailCategory("classical antiquity, and in the Western world traditionally refers to the study of Classical Greek and Latin");
            categoryService.save(category3);
        }

        if(productService.countProduct() == 0){
            Product product = new Product();
            product.setProductName("Malin Kundang");
            product.setProductCategory("Folklore of Indonesia");
            product.setProductStock(3);
            product.setImageLink("https://ebooks.gramedia.com/ebook-covers/12492/big_covers/ID_HHK2014MTH05MKUN_B.jpg");
            product.setCategory(categoryService.get(5));
            productService.saveProduct(product);

            Product product1 = new Product();
            product1.setProductName("House of Leaves");
            product1.setProductCategory("Horror");
            product1.setProductStock(2);
            product1.setImageLink("https://images-na.ssl-images-amazon.com/images/I/810pcXb+l3L.jpg");
            productService.saveProduct(product1);

            Product product2 = new Product();
            product2.setProductName("To Kill a Mockingbird");
            product2.setProductCategory("Classic");
            product2.setProductStock(3);
            product2.setImageLink("https://upload.wikimedia.org/wikipedia/commons/4/4f/To_Kill_a_Mockingbird_%28first_edition_cover%29.jpg");
            productService.saveProduct(product2);

            Product product3 = new Product();
            product3.setProductName("The Great Gatsby");
            product3.setProductCategory("Classic");
            product3.setProductStock(3);
            product3.setImageLink("https://d28hgpri8am2if.cloudfront.net/book_images/onix/cvr9781982146702/the-great-gatsby-9781982146702_hr.jpg");
            productService.saveProduct(product3);

            Product product4 = new Product();
            product4.setProductName("Wuthering Heights");
            product4.setProductCategory("Classic");
            product4.setProductStock(3);
            product4.setImageLink("https://ebooks.gramedia.com/ebook-covers/6297/big_covers/ID_GPU2013MTH07WHEI_B_.jpg");
            productService.saveProduct(product4);

            Product product5 = new Product();
            product5.setProductName("The Story of Timun Mas (Golden Cucumber)");
            product5.setProductCategory("Folklore of Indonesia");
            product5.setProductStock(4);
            product5.setImageLink("https://images-na.ssl-images-amazon.com/images/I/51H%2Bj71AMlL._SX342_SY445_QL70_ML2_.jpg");
            productService.saveProduct(product5);


        }
    }
}
