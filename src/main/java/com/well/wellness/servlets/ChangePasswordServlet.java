package com.well.wellness.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;

public class ChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("student_number") == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        String studentNumber = (String) session.getAttribute("student_number");
        String currentPassword = req.getParameter("currentPassword");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        if (!newPassword.equals(confirmPassword)) {
            req.setAttribute("passwordMessage", "New passwords do not match.");
            req.getRequestDispatcher("dashboard.jsp").forward(req, res);
            return;
        }

        try (Connection conn = DBUtil.getConnection()) {
            // Verify current password
            PreparedStatement ps = conn.prepareStatement("SELECT password FROM users WHERE student_number = ?");
            ps.setString(1, studentNumber);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String hashedPass = rs.getString("password");
                if (!BCrypt.checkpw(currentPassword, hashedPass)) {
                    req.setAttribute("passwordMessage", "Current password is incorrect.");
                    req.getRequestDispatcher("dashboard.jsp").forward(req, res);
                    return;
                }
            } else {
                req.setAttribute("passwordMessage", "User not found.");
                req.getRequestDispatcher("dashboard.jsp").forward(req, res);
                return;
            }

            // Hash new password and update
            String hashedNewPass = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            PreparedStatement updatePs = conn.prepareStatement("UPDATE users SET password = ? WHERE student_number = ?");
            updatePs.setString(1, hashedNewPass);
            updatePs.setString(2, studentNumber);
            updatePs.executeUpdate();

            req.setAttribute("passwordMessage", "Password updated successfully!");
            req.getRequestDispatcher("dashboard.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("passwordMessage", "Server error during password update: " + e.getMessage());
            req.getRequestDispatcher("dashboard.jsp").forward(req, res);
        }
    }
}
