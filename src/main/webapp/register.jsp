<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - BC Wellness</title>
    <link rel="stylesheet" href="css/styles.css">
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const passwordInput = document.getElementById('password');
            const strengthMeter = document.querySelector('.strength-meter-fill');
            const strengthText = document.querySelector('.strength-text');
            const passwordStrength = document.getElementById('password-strength');

            passwordInput.addEventListener('input', function() {
                const password = passwordInput.value;
                const strength = calculatePasswordStrength(password);

                // Update strength meter
                passwordStrength.style.display = 'block';
                strengthMeter.style.width = strength.percentage + '%';
                strengthMeter.style.backgroundColor = strength.color;
                strengthText.textContent = 'Password strength: ' + strength.text;
                strengthText.style.color = strength.color;
            });

            function calculatePasswordStrength(password) {
                let strength = 0;
                const hasUpperCase = /[A-Z]/.test(password);
                const hasLowerCase = /[a-z]/.test(password);
                const hasNumbers = /\d/.test(password);
                const hasSpecial = /[^A-Za-z0-9]/.test(password);
                const isLong = password.length >= 8;

                if (hasUpperCase) strength += 20;
                if (hasLowerCase) strength += 20;
                if (hasNumbers) strength += 20;
                if (hasSpecial) strength += 20;
                if (isLong) strength += 20;

                let result = {
                    percentage: strength,
                    color: '#ff4d4d',
                    text: 'Weak'
                };

                if (strength >= 80) {
                    result.color = '#4CAF50';
                    result.text = 'Strong';
                } else if (strength >= 60) {
                    result.color = '#FFC107';
                    result.text = 'Medium';
                } else if (strength >= 40) {
                    result.color = '#FF9800';
                    result.text = 'Fair';
                }

                return result;
            }
        });
    </script>
</head>
<body>
<% if (request.getAttribute("message") != null) { %>
<div class="flash-message">
    <%= request.getAttribute("message") %>
</div>
<% } %>
<div class="container">
    <header>
        <a href="index.jsp" class="logo">
            <div class="logo-icon">🧠</div>
            <h1>BC Student Wellness</h1>
        </a>
        <nav>
            <a href="index.jsp">Home</a>
            <a href="login.jsp">Login</a>
        </nav>
    </header>

    <main>
        <section class="auth-card">
            <div class="auth-header">
                <h2>Create Account</h2>
                <p>Join our wellness community today</p>
            </div>

            <form action="RegisterServlet" method="post" class="auth-form">
                <% if (request.getAttribute("message") != null) { %>
                <div class="flash-message flash-error">
                    <%= request.getAttribute("message") %>
                </div>
                <% } %>

                <div class="input-group">
                    <label for="student_number">Student Number</label>
                    <input type="text" id="student_number" name="student_number" placeholder="Enter your student number" required>
                </div>

                <div class="input-group">
                    <label for="name">First Name</label>
                    <input type="text" id="name" name="name" placeholder="Enter your first name" required>
                </div>

                <div class="input-group">
                    <label for="surname">Last Name</label>
                    <input type="text" id="surname" name="surname" placeholder="Enter your last name" required>
                </div>

                <div class="input-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" placeholder="Enter your email" required>
                </div>

                <div class="input-group">
                    <label for="phone">Phone</label>
                    <input type="tel" id="phone" name="phone" placeholder="Enter your phone number" required>
                </div>

                <div class="input-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="••••••••" required>
                    <div id="password-strength" class="password-strength">
                        <div class="strength-meter">
                            <div class="strength-meter-fill"></div>
                        </div>
                        <p class="strength-text">Password strength: Weak</p>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary" style="width:100%;">Create Account</button>

                <div class="auth-footer">
                    <p>Already have an account? <a href="login.jsp">Sign in</a></p>
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
