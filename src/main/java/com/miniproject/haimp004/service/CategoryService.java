package com.miniproject.haimp004.service;

import com.miniproject.haimp004.data.Category;
import com.miniproject.haimp004.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listAll(){
        return categoryRepository.findAll();
    }

    public void save(Category category) {

        categoryRepository.save(category);
    }

    public Category get(Integer id) {
        return categoryRepository.findById(id).get();
    }

    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

    public Integer countCategory(){
        return categoryRepository.countCategory();
    }

    public Page<Category> listAllPaging(int page, int size){
        return categoryRepository.findAll(PageRequest.of(page, size));
    }
}
