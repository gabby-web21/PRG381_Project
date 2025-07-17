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
        props.put("mail.smtp.port", "587");
        System.out.println("[EmailUtil] Hardcoded SMTP port: 587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });
        session.setDebug(true); // Enable JavaMail debug output
        System.out.println("[EmailUtil] Using SMTP port: " + props.getProperty("mail.smtp.port"));
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
        System.out.println("[EmailUtil] Preparing to send appointment confirmation email");
        System.out.println("[EmailUtil] toEmail: " + toEmail);
        System.out.println("[EmailUtil] name: " + name);
        System.out.println("[EmailUtil] date: " + date);
        System.out.println("[EmailUtil] time: " + time);
        System.out.println("[EmailUtil] concern: " + concern);
        System.out.println("[EmailUtil] sessionType: " + sessionType);
        try {
            Properties configProps = new Properties();
            InputStream input = EmailUtil.class.getClassLoader().getResourceAsStream("config.properties");
            if (input == null) {
                System.err.println("[EmailUtil] Unable to find config.properties");
                throw new Exception("Unable to find config.properties");
            }
            configProps.load(input);
            System.out.println("[EmailUtil] Loaded config.properties");
            String fromEmail = configProps.getProperty("mail.username", "bcwellnessgroup2@gmail.com");
            String password = configProps.getProperty("mail.password", "fcrf sugx fsar lfyw");
            System.out.println("[EmailUtil] Using sender email: " + fromEmail);
            System.out.println("[EmailUtil] Using app password: " + password);
            Session session = getSession(configProps);
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail, "BC Wellness Admin"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject("Appointment Confirmation - BC Wellness");
            String emailBody = "Dear " + name + ",\n\n" +
                    "Your wellness appointment has been booked successfully.\n\n" +
                    "Date: " + date + "\n" +
                    "Time: " + time + "\n" +
                    "Focus Area: " + concern + "\n" +
                    "Session Type: " + sessionType + "\n\n" +
                    "Thank you for trusting BC Wellness.\n\n" +
                    "Best regards,\n" +
                    "Student Wellness Team";
            msg.setText(emailBody);
            System.out.println("[EmailUtil] Email body:\n" + emailBody);
            Transport.send(msg);
            System.out.println("[EmailUtil] Appointment confirmation email sent successfully to: " + toEmail);
        } catch (Exception e) {
            System.err.println("[EmailUtil] Failed to send appointment confirmation email: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

}
