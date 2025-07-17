package com.well.wellness.servlets;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Properties;

public class EmailUtil {

    private static Session getSession(Properties configProps) {
        final String fromEmail = configProps.getProperty("mail.username");
        final String password = configProps.getProperty("mail.password");

        Properties props = new Properties();
        props.put("mail.smtp.host", configProps.getProperty("mail.smtp.host"));
        props.put("mail.smtp.port", configProps.getProperty("mail.smtp.port"));
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });
        session.setDebug(true); // Enable JavaMail debug output
        return session;
    }

    public static void sendEmail(HttpServletRequest request, String toEmail, String name) throws Exception {
        try {
            Properties configProps = new Properties();
            InputStream input = EmailUtil.class.getClassLoader().getResourceAsStream("config.properties");

            if (input == null) throw new Exception("Unable to find config.properties");
            configProps.load(input);

            Session session = getSession(configProps);
            String fromEmail = configProps.getProperty("mail.username");

            String baseURL = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
            String loginLink = baseURL + "/login.jsp";

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail, "BC Wellness Admin"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject("Welcome to our Wellness System!");
            msg.setText("Dear " + name + ",\n\n" +
                    "Welcome to the Student Wellness Management System! We are so happy to have you! \n\n" +
                    "You can now log in using your credentials at: " + loginLink + "\n\n" +
                    "Kind regards,\n" +
                    "Student Wellness Team");

            Transport.send(msg);
            System.out.println("[EmailUtil] Registration email sent successfully to: " + toEmail);
        } catch (Exception e) {
            System.err.println("[EmailUtil] Failed to send registration email: " + e.getMessage());
            throw e;
        }
    }

    public static void sendResetEmail(HttpServletRequest request, String toEmail, String name, String tempPassword) throws Exception {
        try {
            Properties configProps = new Properties();
            InputStream input = EmailUtil.class.getClassLoader().getResourceAsStream("config.properties");

            if (input == null) throw new Exception("Unable to find config.properties");
            configProps.load(input);

            Session session = getSession(configProps);
            String fromEmail = configProps.getProperty("mail.username");

            String baseURL = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
            String loginLink = baseURL + "/login.jsp";

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail, "BC Wellness Admin"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject("BC Wellness Password Reset");
            msg.setText("Dear " + name + ",\n\n" +
                    "You requested a password reset. Here is your temporary password:\n\n" +
                    tempPassword + "\n\n" +
                    "Please log in at: " + loginLink + " with temporary password given. " +
                    "OPTIONAL: You may change it once logged into the system\n\n" +
                    "Regards,\nBC Wellness Team");

            Transport.send(msg);
            System.out.println("[EmailUtil] Password reset email sent successfully to: " + toEmail);
        } catch (Exception e) {
            System.err.println("[EmailUtil] Failed to send password reset email: " + e.getMessage());
            throw e;
        }
    }

    public static void sendAppointmentConfirmationEmail(HttpServletRequest request, String toEmail, String name,
                                                        String date, String time, String concern, String sessionType) throws Exception {
        try {
            Properties configProps = new Properties();
            InputStream input = EmailUtil.class.getClassLoader().getResourceAsStream("config.properties");
            if (input == null) throw new Exception("Unable to find config.properties");
            configProps.load(input);

            Session session = getSession(configProps);
            String fromEmail = configProps.getProperty("mail.username");

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail, "BC Wellness Admin"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject("Appointment Confirmation - BC Wellness");
            msg.setText("Dear " + name + ",\n\n" +
                    "Your wellness appointment has been booked successfully.\n\n" +
                    "Date: " + date + "\n" +
                    "Time: " + time + "\n" +
                    "Focus Area: " + concern + "\n" +
                    "Session Type: " + sessionType + "\n\n" +
                    "Thank you for trusting BC Wellness.\n\n" +
                    "Best regards,\n" +
                    "Student Wellness Team");
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse("admin@example.com, counselor@bc.edu"));
            Transport.send(msg);
            System.out.println("[EmailUtil] Appointment confirmation email sent successfully to: " + toEmail);
        } catch (Exception e) {
            System.err.println("[EmailUtil] Failed to send appointment confirmation email: " + e.getMessage());
            throw e;
        }
    }

}
