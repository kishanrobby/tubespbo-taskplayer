package com.taskplayer.repository;

import com.taskplayer.model.Todos;
import com.taskplayer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodosRepository extends JpaRepository<Todos, Integer> {
    List<Todos> findByUser(User user);

    @Query("SELECT COUNT(t) FROM Todos t WHERE t.user = :user")
    int countByUser(User user);
}