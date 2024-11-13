package com.example.asmdemo2.Controller;


import com.example.asmdemo2.Entity.CartItem;
import com.example.asmdemo2.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Hiển thị giỏ hàng
    @GetMapping
    public String showCart(Model model) {
        List<CartItem> cartItems = cartService.getCartItems();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", cartService.getTotal());
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity, RedirectAttributes redirectAttributes) {
        if (quantity > 0) {
            cartService.addProduct(productId, quantity);
            redirectAttributes.addFlashAttribute("message", "Sản phẩm đã được thêm vào giỏ hàng!");
        }
        return "redirect:/cart";
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    @PostMapping("/update")
    public String updateCart(@RequestParam Long productId, @RequestParam int quantity) {
        if (quantity <= 0) {
            cartService.removeProduct(productId);
        } else {
            cartService.updateProductQuantity(productId, quantity);
        }
        return "redirect:/cart";
    }

    // Xóa sản phẩm khỏi giỏ hàng
    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long productId) {
        cartService.removeProduct(productId);
        return "redirect:/cart";
    }
}

