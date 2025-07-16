<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BC Student Wellness System</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<div class="container">
    <header>
        <a href="index.jsp" class="logo">
            <div class="logo-icon">🧠</div>
            <h1>BC Student Wellness</h1>
        </a>
        <nav>
            <a href="login.jsp">Login</a>
            <a href="register.jsp" class="btn btn-primary">Register</a>
        </nav>
    </header>

    <main>
        <section class="hero">
            <div class="hero-content">
                <h2>Your Well-being Journey Starts Here</h2>
                <p>Access support, resources, and guidance tailored for BC students. Your mental health matters, and we're here to support you every step of the way.</p>
                <div class="cta-group">
                    <a href="register.jsp" class="btn btn-primary">Get Started</a>
                </div>
            </div>
            <div class="hero-graphic">
                <svg viewBox="0 0 500 400" xmlns="http://www.w3.org/2000/svg">
                    <circle cx="250" cy="200" r="150" fill="rgba(120, 80, 255, 0.1)" stroke="rgba(120, 80, 255, 0.3)" stroke-width="2"/>
                    <circle cx="200" cy="150" r="40" fill="var(--primary)" opacity="0.7"/>
                    <circle cx="300" cy="150" r="30" fill="var(--primary)" opacity="0.7"/>
                    <path d="M180 250 Q250 300 320 250" stroke="var(--primary)" stroke-width="8" fill="none" stroke-linecap="round"/>
                </svg>
            </div>
        </section>
    </main>

    <footer>
        <p>&copy; 2025 BC Wellness. All rights reserved.</p>
        <div class="footer-links">
            <a href="#">Privacy Policy</a>
            <a href="#">Terms of Service</a>
            <a href="#">Contact Us</a>
        </div>
    </footer>
</div>
</html>

