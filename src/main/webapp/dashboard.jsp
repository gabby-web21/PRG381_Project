<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.*, javax.servlet.*" %>
<%
    HttpSession session = request.getSession(false);
    String studentName = (String) session.getAttribute("studentName");
    if (studentName == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<h1>Welcome, <%= studentName %>!</h1>
<p>You are now logged in to the Student Wellness Management System.</p>
<form action="logout" method="post">
    <button type="submit">Logout</button>
</form>
</body>
</html>
