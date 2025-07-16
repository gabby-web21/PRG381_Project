<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Forgot Password</title></head>
<body>
<div class="container">
    <header>
        <h1>BC Student Wellness</h1>
        <nav>
            <a href="login.jsp">Login</a>
            <a href="register.jsp">Register</a>
        </nav>
    </header>

    <main>
        <section class="hero">
            <h2>Forgot Password</h2>
            <form action="forgot-password" method="post">
                <input type="email" name="email" placeholder="Enter your email" required />
                <button type="submit">Reset Password</button>
            </form>
        </section>
    </main>

    <footer>
        <p>&copy; 2025 BC Wellness</p>
    </footer>
</div>
</body>
</html>
