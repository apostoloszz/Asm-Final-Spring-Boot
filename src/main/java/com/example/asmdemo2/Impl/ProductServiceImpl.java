package com.example.asmdemo2.Impl;


import com.example.asmdemo2.Entity.Product;
import com.example.asmdemo2.Repository.ProductRepository;
import com.example.asmdemo2.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findByCategory(Long categoryId) {
        return productRepository.findByCategory_Id(categoryId);
    }
    @Override
    public List<Product> searchProductsByKeyword(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setImage_url(product.getImage_url());
            existingProduct.setCategory(product.getCategory());
            productRepository.save(existingProduct);
        }
        return existingProduct;
    }


//    @Override
//    public Product update(Long id, Product product) {
//        return productRepository.findById(id)
//                .map(existingProduct -> {
//                    existingProduct.setName(product.getName());
//                    existingProduct.setDescription(product.getDescription());
//                    existingProduct.setPrice(product.getPrice());
//                    existingProduct.setImage_url(product.getImage_url());
//                    existingProduct.setCategory(product.getCategory());
//                    return productRepository.save(existingProduct);
//                }).orElseThrow(() -> new RuntimeException("Product not found"));
//    }

}

