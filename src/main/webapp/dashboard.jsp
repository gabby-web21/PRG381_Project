<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession session2 = request.getSession(false);
    if (session2 == null || session2.getAttribute("student_name") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String studentName = (String) session2.getAttribute("student_name");
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
