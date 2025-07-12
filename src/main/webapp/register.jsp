<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<h2>Student Registration</h2>
<form action="register" method="post">
    Student Number: <input type="text" name="studentNumber" required><br>
    Name: <input type="text" name="name" required><br>
    Surname: <input type="text" name="surname" required><br>
    Email: <input type="email" name="email" required><br>
    Phone: <input type="text" name="phone" required><br>
    Password: <input type="password" name="password" required><br>
    <button type="submit">Register</button>
</form>
<a href="index.jsp">Back to Home</a>
</body>
</html>
