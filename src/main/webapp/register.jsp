<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<% if (request.getAttribute("message") != null) { %>
<div class="flash-message">
    <%= request.getAttribute("message") %>
</div>
<% } %>
<div class="container">
    <header>
        <h1>BC Student Wellness</h1>
        <nav>
            <a href="index.jsp">Home</a>
            <a href="login.jsp">Login</a>
        </nav>
    </header>

    <main>
        <section class="hero">
            <h2>Student Registration</h2>
            <form method="post" action="<%= request.getContextPath() %>/register">
                <input type="text" name="student_number" placeholder="Student Number" required><br>
                <input type="text" name="name" placeholder="Name" required><br>
                <input type="text" name="surname" placeholder="Surname" required><br>
                <input type="email" name="email" placeholder="Email" required><br>
                <input type="text" name="phone" placeholder="Phone" required><br>
                <input type="password" name="password" placeholder="Password" required><br>
                <button type="submit">Register</button>
            </form>
        </section>
    </main>

    <footer>
        <p>&copy; 2025 BC Wellness</p>
    </footer>
</div>
</body>
</html>
