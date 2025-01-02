package controllers;

import models.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet(name = "AuthController", urlPatterns = {"/auth"})
public class AuthController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.equals("login")) {
            // Halaman Login
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (action.equals("signup")) {
            // Halaman Sign Up
            request.getRequestDispatcher("signUp.jsp").forward(request, response);
        } else {
            response.sendRedirect("auth?action=login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("signup")) {
            // Proses Sign Up
            handleSignup(request, response);
        } else if (action.equals("login")) {
            // Proses Login
            handleLogin(request, response);
        } else {
            response.sendRedirect("auth?action=login");
        }
    }

    private void handleSignup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validasi input
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Password dan Confirm Password tidak cocok.");
            request.getRequestDispatcher("signUp.jsp").forward(request, response);
            return;
        }

//        // Simpan ke database
//        User user = new User();
//        user.setUsername(username);
//        user.setEmail(email);
//        user.setPassword(password); // Disimpan tanpa hash
        
        // Hash password sebelum disimpan
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Simpan ke database
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(hashedPassword); // Simpan password yang sudah di-hash
    
        if (user.insert()) { // Check if insert was successful
            request.getSession().setAttribute("msg", "Registrasi berhasil! Silakan login.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Gagal menyimpan data pengguna. Coba lagi.");
            request.getRequestDispatcher("signUp.jsp").forward(request, response);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Cek pengguna di database
        User userModel = new User();
        ArrayList<User> users = userModel.get();
        User foundUser = null;

//        for (User user : users) {
//            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
//                foundUser = user;
//                break;
//            }
//        }

        for (User user : users) {
            if (user.getEmail().equals(email) && BCrypt.checkpw(password, user.getPassword())) {
                foundUser = user;
                break;
            }
        }

        if (foundUser != null) {
            // Login berhasil
            HttpSession session = request.getSession();
            session.setAttribute("username", foundUser.getUsername());
            session.setAttribute("email", foundUser.getEmail());
            response.sendRedirect("dashboard.jsp"); // Ganti dengan halaman setelah login
        } else {
            // Login gagal
            request.setAttribute("error", "Email atau password salah.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
