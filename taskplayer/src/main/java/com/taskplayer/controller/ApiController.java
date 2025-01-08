package com.taskplayer.controller;

import com.taskplayer.model.Dashboard;
import com.taskplayer.model.Shop;
import com.taskplayer.model.User;
import com.taskplayer.repository.DashboardRepository;
import com.taskplayer.repository.ShopRepository;
import com.taskplayer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private ShopRepository shopRepository;

    /**
     * Endpoint untuk mendapatkan poin pengguna.
     */
    @GetMapping("/points")
    public ResponseEntity<?> getPoints(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Dashboard dashboard = dashboardRepository.findByUser(user);

        return ResponseEntity.ok(Map.of("points", dashboard.getPoints()));
    }

    /**
     * Endpoint untuk membeli item di Shop.
     */
    @PostMapping("/shop/buy")
    public ResponseEntity<?> buyItem(Authentication authentication, @RequestBody Map<String, Object> payload) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Dashboard dashboard = dashboardRepository.findByUser(user);

        String itemName = (String) payload.get("character");
        int price = (int) payload.get("price");

        if (dashboard.getPoints() < price) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Insufficient points."));
        }

        dashboard.setPoints(dashboard.getPoints() - price);
        dashboardRepository.save(dashboard);

        Shop shopItem = shopRepository.findByUserAndItemName(user, itemName)
                .orElseGet(() -> {
                    Shop newItem = new Shop();
                    newItem.setUser(user);
                    newItem.setItemName(itemName);
                    return newItem;
                });

        shopItem.incrementItemTotal(1);
        shopRepository.save(shopItem);

        return ResponseEntity.ok(Map.of("message", "Purchase successful!"));
    }
}