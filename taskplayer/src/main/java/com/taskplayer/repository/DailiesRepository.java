package com.taskplayer.repository;

import com.taskplayer.model.Dailies;
import com.taskplayer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DailiesRepository extends JpaRepository<Dailies, Integer> {
    List<Dailies> findByUser(User user);

    @Query("SELECT COUNT(d) FROM Dailies d WHERE d.user = :user")
    int countByUser(User user);
}