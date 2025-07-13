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
<div style="color: red;"><%= request.getAttribute("message") %></div>
<% } %>
<h2>Student Login</h2>
<form action="login" method="post">
    Email: <input type="email" name="email" required><br>
    Password: <input type="password" name="password" required><br>
    <button type="submit">Login</button>
</form>
<a href="register.jsp">New here? Register</a><br>
<a href="index.jsp">Back to Home</a>
</body>
</html>
