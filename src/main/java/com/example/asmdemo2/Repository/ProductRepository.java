package com.example.asmdemo2.Repository;


import com.example.asmdemo2.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Tìm sản phẩm theo ID danh mục
    List<Product> findByCategory_Id(Long categoryId);
    // Tìm sản phẩm theo tên chứa từ khóa
    List<Product> findByNameContaining(String keyword);
}
