package com.taskplayer.repository;

import com.taskplayer.model.Habits;
import com.taskplayer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HabitsRepository extends JpaRepository<Habits, Integer> {
    List<Habits> findByUser(User user);
    
    @Query("SELECT COUNT(h) FROM Habits h WHERE h.user = :user")
    int countByUser(User user);
}