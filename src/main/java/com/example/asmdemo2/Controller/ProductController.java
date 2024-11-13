package com.example.asmdemo2.Controller;

import com.example.asmdemo2.Entity.Product;
import com.example.asmdemo2.Entity.Category;
import com.example.asmdemo2.Service.CategoryService;
import com.example.asmdemo2.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    // Hiển thị danh sách sản phẩm
    @GetMapping
    public String showProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("title", "Product List");
        model.addAttribute("bodyPage", "product-list.jsp");
        return "layout";
    }

    // Hiển thị form tạo sản phẩm mới
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);  // Thêm danh mục vào model
        return "product/add-product";
    }

    // Lưu sản phẩm mới
    @PostMapping
    public String createProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "Product created successfully!");
        return "redirect:/products";
    }

    // Hiển thị form chỉnh sửa sản phẩm
    @GetMapping("{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/products";
        }
        List<Category> categories = categoryService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "product/edit-product";
    }

    // Cập nhật sản phẩm
    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        Product updatedProduct = productService.update(id, product);
        if (updatedProduct == null) {
            redirectAttributes.addFlashAttribute("error", "Product not found!");
            return "redirect:/products";
        }
        redirectAttributes.addFlashAttribute("message", "Product updated successfully!");
        return "redirect:/products";
    }

    // Xóa sản phẩm
    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/products";
    }

    // Tìm kiếm sản phẩm
    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productService.searchProductsByKeyword(keyword);
        model.addAttribute("products", products);
        model.addAttribute("title", "Search Results for '" + keyword + "'");
        model.addAttribute("bodyPage", "product-list.jsp");
        return "layout";
    }
}
