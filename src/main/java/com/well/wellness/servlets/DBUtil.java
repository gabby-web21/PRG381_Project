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
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/wellness",
                    "postgres",
                    "yourpassword"
            );
        } catch (Exception e) {
            System.err.println("DB connection failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
//ENSURE BEFORE RUNNING THAT YOU PUT IN YOUR ACTUAL PASSWORD FOR PSQL username is automatically postgres, unless changed when installing.
