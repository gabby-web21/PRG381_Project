package com.well.wellness.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.regex.Pattern;
import org.mindrot.jbcrypt.BCrypt;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String studentNumber = req.getParameter("student_number");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        // Field validation
        if (!isValidEmail(email) || !isValidPhone(phone) || password.length() < 6) {
            req.setAttribute("message", "Invalid input: Check email, phone, and password.");
            req.getRequestDispatcher("register.jsp").forward(req, res);
            return;
        }

        try (Connection conn = DBUtil.getConnection()) {
            // Check if email or student number already exists
            PreparedStatement checkStmt = conn.prepareStatement(
                    "SELECT * FROM users WHERE email = ? OR student_number = ?");
            checkStmt.setString(1, email);
            checkStmt.setString(2, studentNumber);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                req.setAttribute("message", "User already exists.");
                req.getRequestDispatcher("register.jsp").forward(req, res);
            } else {
                String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

                PreparedStatement insertStmt = conn.prepareStatement(
                        "INSERT INTO users(student_number, name, surname, email, phone, password) VALUES (?, ?, ?, ?, ?, ?)");
                insertStmt.setString(1, studentNumber);
                insertStmt.setString(2, name);
                insertStmt.setString(3, surname);
                insertStmt.setString(4, email);
                insertStmt.setString(5, phone);
                insertStmt.setString(6, hashedPassword);
                insertStmt.executeUpdate();

                req.setAttribute("message", "Registration successful. Please login.");
                req.getRequestDispatcher("login.jsp").forward(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(500, "Server error during registration.");
        }
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$", email);
    }

    private boolean isValidPhone(String phone) {
        return Pattern.matches("^[0-9]{10}$", phone);
    }
}
