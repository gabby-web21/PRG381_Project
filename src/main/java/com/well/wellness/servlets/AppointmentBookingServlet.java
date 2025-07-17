package com.well.wellness.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AppointmentBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("[AppointmentBookingServlet] doPost triggered");
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("student_number") == null) {
            System.out.println("[AppointmentBookingServlet] No session or student_number, redirecting to login.jsp");
            res.sendRedirect("login.jsp");
            return;
        }

        String studentNumber = (String) session.getAttribute("student_number");
        String studentName = (String) session.getAttribute("student_name");

        String date = req.getParameter("date");
        String time = req.getParameter("time");
        String concern = req.getParameter("concern"); // display text
        String concernValue = req.getParameter("concernValue"); // value
        String sessionType = req.getParameter("session-type"); // display text
        String sessionTypeValue = req.getParameter("sessionTypeValue"); // value
        String requests = req.getParameter("requests");

        System.out.println("[DEBUG] Received from AJAX:");
        System.out.println("date=" + date);
        System.out.println("time=" + time);
        System.out.println("concern=" + concern);
        System.out.println("concernValue=" + concernValue);
        System.out.println("sessionType=" + sessionType);
        System.out.println("sessionTypeValue=" + sessionTypeValue);
        System.out.println("requests=" + requests);

        boolean dbSuccess = false;
        // Save appointment to DB if you want (optional)
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO appointments (student_number, date, time, concern, session_type, requests) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, studentNumber);
            ps.setString(2, date);
            ps.setString(3, time);
            ps.setString(4, concern); // store display text
            ps.setString(5, sessionType); // store display text
            ps.setString(6, requests);
            ps.executeUpdate();
            dbSuccess = true;
            System.out.println("[AppointmentBookingServlet] Appointment saved to DB for student: " + studentNumber);
        } catch(Exception e) {
            System.err.println("[AppointmentBookingServlet] Error saving appointment: " + e.getMessage());
            e.printStackTrace();
            req.setAttribute("bookingMessage", "Error saving appointment.");
            // Don't return here; still attempt to send email
        }

        // Send confirmation email (always attempt)
        try {
            String toEmail = getEmailByStudentNumber(studentNumber); // implement method to get email from DB
            System.out.println("[AppointmentBookingServlet] Sending confirmation email to: " + toEmail);
            EmailUtil.sendAppointmentConfirmationEmail(req, toEmail, studentName, date, time, concern, sessionType);
            System.out.println("[AppointmentBookingServlet] Confirmation email sent to: " + toEmail);
        } catch (Exception e) {
            System.err.println("[AppointmentBookingServlet] Failed to send confirmation email: " + e.getMessage());
            e.printStackTrace();
            req.setAttribute("bookingMessage", "Failed to send confirmation email.");
            // Don't return here; allow redirect to dashboard
        }

        if (dbSuccess) {
            // Optionally log or handle success
        } else {
            // Optionally log or handle failure
        }
        res.setContentType("application/json");
        res.getWriter().write("{\"status\":\"ok\"}");
        // Do not redirect
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

