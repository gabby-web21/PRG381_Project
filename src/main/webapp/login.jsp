<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - BC Wellness</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<%
    String success = request.getParameter("success");
    if ("true".equals(success)) {
%>
<script>
    alert("Registration successful! Please log in to continue.");
</script>
<%
    }
%>
<% if (request.getAttribute("message") != null) { %>
<div class="flash-message">
    <%= request.getAttribute("message") %>
</div>
<% } %>
<%@ page import="java.net.URLDecoder" %>
<%
    String resetParam = request.getParameter("reset");
    if ("success".equals(resetParam)) {
%>
<div style="background-color: #d4edda; color: #155724; padding: 10px; border: 1px solid #c3e6cb; border-radius: 5px; margin: 15px 0;">
    A temporary password has been sent to your email! Please log in and change it.
</div>
<%
    }
%>

<div class="container">
    <header>
        <a href="index.jsp" class="logo">
            <div class="logo-icon">🧠</div>
            <h1>BC Student Wellness</h1>
        </a>
        <nav>
            <a href="index.jsp">Home</a>
            <a href="register.jsp">Register</a>
        </nav>
    </header>

    <main>
        <section class="auth-card">
            <div class="auth-header">
                <h2>Welcome Back</h2>
                <p>Sign in to continue your wellness journey</p>
            </div>

            <form action="LoginServlet" method="post" class="auth-form">
                <% if (request.getAttribute("message") != null) { %>
                <div class="flash-message flash-error">
                    <%= request.getAttribute("message") %>
                </div>
                <% } %>

                <div class="input-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" placeholder="Enter your email" required>
                </div>

                <div class="input-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="••••••••" required>
                </div>

                <div class="form-options">
                    <div class="remember">
                        <input type="checkbox" id="remember">
                        <label for="remember">Remember me</label>
                    </div>
                    <a href="forgotPassword.jsp">Forgot Password?</a>
                </div>

                <button type="submit" class="btn btn-primary" style="width:100%;">Sign In</button>

                <div class="auth-footer">
                    <p>Don't have an account? <a href="register.jsp">Register now</a></p>
                </div>
            </form>
        </section>
    </main>

    <footer>
        <p>&copy; 2025 BC Wellness</p>
    </footer>
</div>
</body>
</html>
