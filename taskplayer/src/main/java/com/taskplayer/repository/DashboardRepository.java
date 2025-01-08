package com.taskplayer.repository;

import com.taskplayer.model.Dashboard;
import com.taskplayer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Integer> {
    Dashboard findByUser(User user);
}