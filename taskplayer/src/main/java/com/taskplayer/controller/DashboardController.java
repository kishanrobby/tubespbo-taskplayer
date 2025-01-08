package com.taskplayer.controller;

import com.taskplayer.model.Dashboard;
import com.taskplayer.model.Shop;
import com.taskplayer.model.User;
import com.taskplayer.repository.DashboardRepository;
import com.taskplayer.repository.UserRepository;
import com.taskplayer.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping("/dashboard")
    public String dashboardPage(Authentication authentication, Model model) {
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Dashboard dashboard = dashboardRepository.findByUser(user);
        List<Shop> shopItems = shopRepository.findByUser(user);

        Map<String, Integer> itemTotals = new HashMap<>();
        itemTotals.put("GARUDA", 0);
        itemTotals.put("PIGLET", 0);
        itemTotals.put("FROGY", 0);
        itemTotals.put("THE GOAT", 0);

        for (Shop shopItem : shopItems) {
            itemTotals.put(shopItem.getItemName(), shopItem.getItemTotal());
        }

        model.addAttribute("name", user.getUsername());
        model.addAttribute("points", dashboard.getPoints());
        model.addAttribute("taskFinished", dashboard.getTaskFinished());
        model.addAttribute("taskPending", dashboard.getTaskPending());
        model.addAttribute("itemTotals", itemTotals);

        return "dashboard";
    }
}