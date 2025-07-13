package com.well.wellness.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        if (!isValidEmail(email) || !isValidPhone(phone) || password.length() < 6) {
            req.setAttribute("message", "Invalid input: Check email, phone, and password.");
            req.getRequestDispatcher("register.jsp").forward(req, res);
            return;
        }

        try (Connection conn = DBUtil.getConnection()) {
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
                req.getRequestDispatcher("index.jsp").forward(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Server error during registration: " + e.getMessage());
            req.getRequestDispatcher("register.jsp").forward(req, res);
        }
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$", email);
    }

    private boolean isValidPhone(String phone) {
        return Pattern.matches("^[0-9]{10}$", phone);
    }
}
