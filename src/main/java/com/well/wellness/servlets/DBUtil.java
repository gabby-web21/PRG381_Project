package com.well.wellness.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;

import org.mindrot.jbcrypt.BCrypt;

public class DBUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/wellness";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            initializeDatabase(conn); // This line creates the table if needed
            return conn;
        } catch (Exception e) {
            System.err.println("DB connection failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static void initializeDatabase(Connection conn) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "student_number VARCHAR(10) PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "surname VARCHAR(50) NOT NULL," +
                "email VARCHAR(100) UNIQUE NOT NULL," +
                "phone VARCHAR(20) NOT NULL," +
                "password TEXT NOT NULL" +
                ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableSQL);
            System.out.println("Users table checked/created.");
        } catch (SQLException e) {
            System.err.println("Error creating users table: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
//ENSURE BEFORE RUNNING THAT YOU PUT IN YOUR ACTUAL PASSWORD FOR PSQL username is automatically postgres, unless changed when installing.
