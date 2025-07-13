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

<h2>Student Login</h2>
<form action="login" method="post">
    Email: <input type="email" name="email" required><br>
    Password: <input type="password" name="password" required><br>
    <button type="submit">Login</button>
</form>
<a href="register.jsp">New here? Register</a><br>
<a href="index.jsp">Back to Home</a>
<a href="forgotPassword.jsp">Forgot Password? Reset it here!</a>
</body>
</html>
