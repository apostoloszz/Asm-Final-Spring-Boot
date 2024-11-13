package com.example.asmdemo2.Impl;

import com.example.asmdemo2.Repository.CategoryRepository;
import com.example.asmdemo2.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.asmdemo2.Entity.Category;


import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    // Bạn có thể thêm các phương thức khác nếu cần
//    @Override
//    public Category findById(Long id) {
//        return categoryRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public void save(Category category) {
//        categoryRepository.save(category);
//    }
//
//    @Override
//    public void update(Long id, Category category) {
//        Category existingCategory = findById(id);
//        if (existingCategory != null) {
//            existingCategory.setName(category.getName());
//            categoryRepository.save(existingCategory);
//        }
//    }
//
//    @Override
//    public void delete(Long id) {
//        categoryRepository.deleteById(id);
//    }
}
