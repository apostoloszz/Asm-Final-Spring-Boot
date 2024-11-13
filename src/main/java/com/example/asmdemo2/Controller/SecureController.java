package com.example.asmdemo2.Controller;

import com.example.asmdemo2.Entity.Customer;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecureController {

    @GetMapping("/secure-page")
    public String securePage(HttpSession session, Model model) {
        Customer loggedInUser = (Customer) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", loggedInUser);
        return "page/secure-page";
    }

}
