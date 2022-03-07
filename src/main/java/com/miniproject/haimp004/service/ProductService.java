package com.miniproject.haimp004.service;

import com.miniproject.haimp004.data.Product;
import com.miniproject.haimp004.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listAll(){
        return productRepository.findAll();
    }

    public void saveProduct(Product product){
        productRepository.save(product);
    }

    public Product get(Integer id){
        return  productRepository.findById(id).get();
    }

    public void deleteProduct(Integer id){
        productRepository.deleteById(id);
    }

    public Integer countProduct(){
        return productRepository.countProduct();
    }

    public Page<Product> listAllPaging(int page, int size){
        return productRepository.findAll(PageRequest.of(page, size));
    }
}
