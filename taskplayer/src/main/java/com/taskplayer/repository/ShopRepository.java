package com.taskplayer.repository;

import com.taskplayer.model.Shop;
import com.taskplayer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    List<Shop> findByUser(User user);
    Optional<Shop> findByUserAndItemName(User user, String itemName);
}