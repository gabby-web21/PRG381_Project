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

import org.mindrot.jbcrypt.BCrypt;


public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if(email == null || password == null) {
            req.setAttribute("message", "Email and password are required.");
            req.getRequestDispatcher("login.jsp").forward(req, res);
            return;
        }

        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPass = rs.getString("password");
                if (BCrypt.checkpw(password, hashedPass)) {
                    HttpSession session = req.getSession();
                    session.setAttribute("student_name", rs.getString("name"));
                    session.setAttribute("student_number", rs.getString("student_number"));
                    res.sendRedirect("dashboard.jsp");
                } else {
                    req.setAttribute("message", "Incorrect password.");
                    req.getRequestDispatcher("login.jsp").forward(req, res);
                }
            } else {
                req.setAttribute("message", "User not found.");
                req.getRequestDispatcher("login.jsp").forward(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Server error during login: " + e.getMessage());
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }
    }
}
