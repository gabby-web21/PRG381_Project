package com.well.wellness.servlets;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.InputStream;
import java.util.Properties;

public class EmailUtil {

    public static void sendEmail(String toEmail, String name) throws Exception {
        Properties configProps = new Properties();
        InputStream input = EmailUtil.class.getClassLoader().getResourceAsStream("config.properties");

        if (input == null) {
            throw new Exception("Unable to find config.properties");
        }

        configProps.load(input);

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

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(fromEmail, "BC Wellness Admin"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        msg.setSubject("Welcome to the Wellness System ");
        msg.setText("Dear " + name + ",\n\n" +
                "Welcome to the BC Student Wellness Management System! We're happy to have you 😊\n\n" +
                "You can now log in using your credentials at: http://localhost:8081/PRGPROJTEST/login.jsp\n\n" +
                "Best regards,\n" +
                "Student Wellness Team");

        Transport.send(msg);
    }
}

