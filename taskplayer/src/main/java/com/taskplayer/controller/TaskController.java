package com.taskplayer.controller;

import com.taskplayer.model.Dailies;
import com.taskplayer.model.Habits;
import com.taskplayer.model.Todos;
import com.taskplayer.model.User;
import com.taskplayer.model.Dashboard;
import com.taskplayer.repository.DailiesRepository;
import com.taskplayer.repository.HabitsRepository;
import com.taskplayer.repository.TodosRepository;
import com.taskplayer.repository.UserRepository;
import com.taskplayer.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private HabitsRepository habitsRepository;

    @Autowired
    private DailiesRepository dailiesRepository;

    @Autowired
    private TodosRepository todosRepository;

    @GetMapping
    public String taskPage(Authentication authentication, Model model) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Habits> habits = habitsRepository.findByUser(user);
        List<Dailies> dailies = dailiesRepository.findByUser(user);
        List<Todos> todos = todosRepository.findByUser(user);
        Dashboard dashboard = dashboardRepository.findByUser(user);

        model.addAttribute("habits", habits);
        model.addAttribute("dailies", dailies);
        model.addAttribute("todos", todos);
        model.addAttribute("points", dashboard.getPoints());

        return "task";
    }

    @PostMapping("/habits")
    public String addHabit(@RequestParam String title, 
                           @RequestParam String description, 
                           @RequestParam String type, 
                           Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Habits habit = new Habits();
        habit.setUser(user);
        habit.setTitle(title);
        habit.setDescription(description);
        habit.setType(type);

        Dashboard dashboard = dashboardRepository.findByUser(user);
        dashboard.incrementTaskPending();
        dashboardRepository.save(dashboard);

        habitsRepository.save(habit);

        return "redirect:/task";
    }

    @PostMapping("/dailies")
    public String addDaily(@RequestParam String title, 
                           @RequestParam String description, 
                           @RequestParam String frequency, 
                           Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Dailies daily = new Dailies();
        daily.setUser(user);
        daily.setTitle(title);
        daily.setDescription(description);
        daily.setFrequency(frequency);

        Dashboard dashboard = dashboardRepository.findByUser(user);
        dashboard.incrementTaskPending();
        dashboardRepository.save(dashboard);

        dailiesRepository.save(daily);

        return "redirect:/task";
    }

    @PostMapping("/todos")
    public String addTodo(@RequestParam String title, 
                          @RequestParam String description, 
                          @RequestParam(required = false) String dueDate, 
                          Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Todos todo = new Todos();
        todo.setUser(user);
        todo.setTitle(title);
        todo.setDescription(description);
        if (dueDate != null && !dueDate.isEmpty()) {
            todo.setDueDate(java.sql.Date.valueOf(dueDate));
        }

        Dashboard dashboard = dashboardRepository.findByUser(user);
        dashboard.incrementTaskPending();
        dashboardRepository.save(dashboard);

        todosRepository.save(todo);

        return "redirect:/task";
    }

    @PostMapping("/habits/{id}/complete")
    public String completeHabit(@PathVariable int id, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Habits habit = habitsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));
        habitsRepository.delete(habit);

        Dashboard dashboard = dashboardRepository.findByUser(user);
        dashboard.incrementPoints(10); // 10 poin
        dashboard.incrementTaskFinished();
        dashboardRepository.save(dashboard);

        return "redirect:/task";
    }

    @PostMapping("/dailies/{id}/complete")
    public String completeDaily(@PathVariable int id, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Dailies daily = dailiesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Daily not found"));
        dailiesRepository.delete(daily);

        Dashboard dashboard = dashboardRepository.findByUser(user);
        dashboard.incrementPoints(15); // 15 poin
        dashboard.incrementTaskFinished();
        dashboardRepository.save(dashboard);

        return "redirect:/task";
    }

    @PostMapping("/todos/{id}/complete")
    public String completeTodo(@PathVariable int id, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Todos todo = todosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todosRepository.delete(todo);

        Dashboard dashboard = dashboardRepository.findByUser(user);
        dashboard.incrementPoints(5); // 5 poin
        dashboard.incrementTaskFinished();
        dashboardRepository.save(dashboard);

        return "redirect:/task";
    }
}