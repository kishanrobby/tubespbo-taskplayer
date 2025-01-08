package com.taskplayer.controller;

import com.taskplayer.model.Shop;
import com.taskplayer.model.User;
import com.taskplayer.repository.DashboardRepository;
import com.taskplayer.repository.ShopRepository;
import com.taskplayer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShopController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private DashboardRepository dashboardRepository;

    @GetMapping("/shop")
    public String shopPage(Authentication authentication, Model model) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Shop> shopItems = shopRepository.findAll();

        model.addAttribute("points", dashboardRepository.findByUser(user).getPoints());
        model.addAttribute("shopItems", shopItems);

        return "shop";
    }
}