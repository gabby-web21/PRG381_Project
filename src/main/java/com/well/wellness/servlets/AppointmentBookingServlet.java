package com.well.wellness.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AppointmentBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("student_number") == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        String studentNumber = (String) session.getAttribute("student_number");
        String studentName = (String) session.getAttribute("student_name");

        String date = req.getParameter("date");
        String time = req.getParameter("time");
        String concern = req.getParameter("concern");
        String sessionType = req.getParameter("session-type");
        String requests = req.getParameter("requests");

        // Save appointment to DB if you want (optional)
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO appointments (student_number, date, time, concern, session_type, requests) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, studentNumber);
            ps.setString(2, date);
            ps.setString(3, time);
            ps.setString(4, concern);
            ps.setString(5, sessionType);
            ps.setString(6, requests);
            ps.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
            req.setAttribute("bookingMessage", "Error saving appointment.");
            req.getRequestDispatcher("dashboard.jsp").forward(req, res);
            return;
        }

        // Send confirmation email
        try {
            String toEmail = getEmailByStudentNumber(studentNumber); // implement method to get email from DB
            EmailUtil.sendAppointmentConfirmationEmail(req, toEmail, studentName, date, time, concern, sessionType);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("bookingMessage", "Failed to send confirmation email.");
            req.getRequestDispatcher("dashboard.jsp").forward(req, res);
            return;
        }

        HttpSession session2 = req.getSession();
        session2.setAttribute("bookingMessage", "Appointment booked successfully!");
        res.sendRedirect("dashboard.jsp");

    }

    // Add method to fetch email by student number
    private String getEmailByStudentNumber(String studentNumber) throws Exception {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT email FROM users WHERE student_number = ?");
            ps.setString(1, studentNumber);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getString("email");
            } else {
                throw new Exception("Email not found for student number " + studentNumber);
            }
        }
    }
}

