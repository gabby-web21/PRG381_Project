package com.well.wellness.servlets;

import com.well.wellness.servlets.EmailUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;


public class ForgotPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String email = req.getParameter("email");

        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");

                // Optional: generate temp token or password here
                String tempPassword = generateRandomPassword();
                String hashedPassword = BCrypt.hashpw(tempPassword, BCrypt.gensalt());

                PreparedStatement update = conn.prepareStatement("UPDATE users SET password = ? WHERE email = ?");
                update.setString(1, hashedPassword);
                update.setString(2, email);
                update.executeUpdate();

                EmailUtil.sendResetEmail(req, email, name, tempPassword);

                req.setAttribute("message", "Check your email for a temporary password.");
                res.sendRedirect("login.jsp?reset=success");
            } else {
                req.setAttribute("message", "Email not found.");
                req.getRequestDispatcher("forgotPassword.jsp").forward(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Error: " + e.getMessage());
            req.getRequestDispatcher("forgotPassword.jsp").forward(req, res);
        }
    }

    private String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
