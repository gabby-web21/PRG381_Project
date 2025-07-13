<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Forgot Password</title></head>
<body>
<h2>Forgot Your Password?</h2>
<form action="forgot-password" method="post">
    Enter your registered email: <input type="email" name="email" required><br>
    <button type="submit">Send Reset Link</button>
</form>
<a href="login.jsp">Back to login</a>
</body>
</html>
