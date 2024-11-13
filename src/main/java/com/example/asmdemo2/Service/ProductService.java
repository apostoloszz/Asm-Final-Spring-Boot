package com.example.asmdemo2.Service;


import com.example.asmdemo2.Entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    List<Product> findByCategory(Long categoryId);
    void save(Product product);
    void delete(Long id);
    Product update(Long id, Product product);
    List<Product> searchProductsByKeyword(String keyword); // Thêm phương thức cập nhật
}
