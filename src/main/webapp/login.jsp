<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
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
        <h1>BC Student Wellness</h1>
        <nav>
            <a href="index.jsp">Home</a>
            <a href="register.jsp">Register</a>
            <a href="forgotPassword.jsp">Forgot Password? Reset it here!</a>
        </nav>
    </header>

    <main>
        <section class="hero">
            <h2>Login</h2>
            <form method="post" action="<%= request.getContextPath() %>/login">
                <input type="text" name="email" placeholder="Email" required />
                <input type="password" name="password" placeholder="Password" required />
                <button type="submit">Login</button>
            </form>
        </section>
    </main>

    <footer>
        <p>&copy; 2025 BC Wellness</p>
    </footer>
</div>
</body>
</html>
