package com.taskplayer.controller;

import com.taskplayer.model.User;
import com.taskplayer.model.Dashboard;
import com.taskplayer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Email atau password salah.");
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            RedirectAttributes redirectAttributes) {

        User user = userRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);

        if (user != null) {
            return "redirect:/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Email atau password salah.");
            return "redirect:/login";
        }
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute User user,
            RedirectAttributes redirectAttributes) {
    
        String usernamePattern = "^[a-zA-Z0-9_-]{1,20}$";
        if (!user.getUsername().matches(usernamePattern)) {
            redirectAttributes.addFlashAttribute("error", "Username must be 1 to 20 characters, containing only letters a to z, numbers 0 to 9, hyphens, or underscores.");
            return "redirect:/register";
        }

        String[] inappropriateTerms = {"jancok", "anying"};
        for (String term : inappropriateTerms) {
            if (user.getUsername().toLowerCase().contains(term)) {
                redirectAttributes.addFlashAttribute("error", "Username contains inappropriate terms.");
                return "redirect:/register";
            }
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            redirectAttributes.addFlashAttribute("error", "Username sudah terdaftar.");
            return "redirect:/register";
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            redirectAttributes.addFlashAttribute("error", "Email sudah terdaftar.");
            return "redirect:/register";
        }
    
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    
        Dashboard dashboard = new Dashboard();
        dashboard.setUser(user);
        dashboard.setPoints(0);
        dashboard.setTaskPending(0);
        dashboard.setTaskFinished(0);
    
        user.setDashboard(dashboard);
    
        userRepository.save(user);
    
        redirectAttributes.addFlashAttribute("success", "Daftar akun berhasil.");
        return "redirect:/login";
    }       
}