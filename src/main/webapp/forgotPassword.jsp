<%@ page contentType="text/html;charset=UTF-8" language="java" %><% if (request.getAttribute("message") != null) { %>
<div class="flash-message flash-error">
    <%= request.getAttribute("message") %>
</div>
<% } %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgot Password - BC Wellness</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
    <div class="container">
        <header>
            <a href="index.jsp" class="logo">
                <div class="logo-icon">🧠</div>
                <h1>BC Student Wellness</h1>
                <br><br>
                <br><br>
            </a>
            <nav>
                <a href="login.jsp">Login</a>
                <a href="register.jsp">Register</a>
            </nav>
        </header>

        <main>
            <section class="auth-card">
                <div class="auth-header">
                    <h2>Reset Your Password</h2>
                    <p>Enter your email to receive a reset link</p>
                </div>

                <form action="ForgotPasswordServlet" method="post" class="auth-form">
                    <div class="input-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" placeholder="Enter your email" required>
                    </div>

                    <button type="submit" class="btn btn-primary" style="width:100%;">Reset Password</button>

                    <div class="auth-footer">
                        <p>Remember your password? <a href="login.jsp">Sign in</a></p>
                    </div>
                </form>
            </section>
        </main>

        <footer>
            <p>&copy; 2025 BC Wellness</p>
        </footer>
    </div>
</div>
</body>
</html>
