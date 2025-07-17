<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
HttpSession session2 = request.getSession(false);
if (session2 == null || session2.getAttribute("student_name") == null) {
response.sendRedirect("login.jsp");
return;
}
String studentName = (String) session2.getAttribute("student_name");
%>
<% if (request.getAttribute("passwordMessage") != null) { %>
<div class="flash-message success">
    <%= request.getAttribute("passwordMessage") %>
</div>
<% } %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - BC Wellness</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
    <div class="dashboard-header">
        <a href="index.jsp" class="logo">
            <div class="logo-icon">🧠</div>
            <h1>BC Student Wellness</h1>
        </a>
        <div class="user-info">
            <div class="user-avatar"><%= studentName.charAt(0) %></div>
            <span><%= studentName %></span>
            <form action="LogoutServlet" method="post" style="margin-left:20px;">
                <button type="submit" class="btn btn-outline">Logout</button>
            </form>
        </div>
    </div>
    <main>
        <section class="welcome-banner">
            <h2>Welcome back, <%= studentName %>!</h2>
            <p>Your mental wellness journey is important to us. Here's how we can support you today.</p>
        </section>

        <!-- Mental Health Focus Section -->
        <div class="wellness-section">
            <div class="wellness-content">
                <h3>Your Mental Health Matters</h3>
                <p>Taking care of your mental health is just as important as your physical health. Regular check-ins with a wellness counselor can help you navigate challenges and build resilience.</p>
                <p>Did you know? Students who engage with counseling services report a 30% improvement in academic performance and overall well-being.</p>
            </div>
            <div class="wellness-quote">
                <blockquote>
                    "Mental health... is not a destination, but a process. It's about how you drive, not where you're going."
                    <cite>— Somebody</cite>
                </blockquote>
            </div>
        </div>

        <!-- Appointment Booking Card -->
        <div class="booking-card">
            <div class="booking-header">
                <h3>Schedule Your Wellness Session</h3>
            </div>

            <form id="appointment-form" class="booking-form" action="AppointmentBookingServlet" method="post">

                <div class="form-row">
                    <div class="input-group">
                        <label for="appointment-date">Preferred Date</label>
                        <input type="date" id="appointment-date" name="date" required min="<%= new java.util.Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().plusDays(1).toString() %>">
                    </div>

                    <div class="input-group">
                        <label for="appointment-time">Preferred Time</label>
                        <select id="appointment-time" name="time" required>
                            <option value="">Select a time</option>
                            <option value="9:00 AM">9:00 AM</option>
                            <option value="10:30 AM">10:30 AM</option>
                            <option value="12:00 PM">12:00 PM</option>
                            <option value="1:30 PM">1:30 PM</option>
                            <option value="3:00 PM">3:00 PM</option>
                            <option value="4:30 PM">4:30 PM</option>
                        </select>
                    </div>
                </div>

                <div class="form-row">
                    <div class="input-group">
                        <label for="concern-type">Focus Area</label>
                        <select id="concern-type" name="concern">
                            <option value="general">General Wellness Check-in</option>
                            <option value="academic">Academic Stress</option>
                            <option value="relationships">Relationships</option>
                            <option value="anxiety">Anxiety/Stress</option>
                            <option value="depression">Depression</option>
                            <option value="other">Other Concern</option>
                        </select>
                    </div>

                    <div class="input-group">
                        <label for="session-type">Session Type</label>
                        <select id="session-type" name="session-type">
                            <option value="in-person">In-Person</option>
                            <option value="video">Video Call</option>
                            <option value="phone">Phone Call</option>
                        </select>
                    </div>
                </div>

                <div class="input-group">
                    <label for="special-requests">Special Requests</label>
                    <textarea id="special-requests" name="requests" placeholder="Any preferences for your counselor or specific concerns?"></textarea>
                </div>

                <button type="submit" class="btn btn-primary" style="width:100%;">Book Appointment</button>
            </form>
        </div>

        <!-- Confirmation Card -->
        <div id="confirmation-card" class="confirmation-card">
            <div class="confirmation-icon">✓</div>
            <h3>Appointment Confirmed!</h3>
            <p>Your wellness session has been successfully scheduled.</p>

            <div class="confirmation-details">
                <p><span>Date:</span> <span id="conf-date">Monday, July 21, 2025</span></p>
                <p><span>Time:</span> <span id="conf-time">10:30 AM</span></p>
                <p><span>Focus Area:</span> <span id="conf-concern">General Wellness Check-in</span></p>
                <p><span>Session Type:</span> <span id="conf-type">In-Person</span></p>
                <p><span>Location:</span> <span id="conf-location">Wellness Center, Room 205</span></p>
            </div>

            <p>We've sent a confirmation email with these details to your student email address.</p>
            <button id="new-booking-btn" class="btn btn-outline btn-large">Book Another Session</button>
        </div>
    </main>
</div>

<!-- NEW PASSWORD UPDATE SECTION -->
<div class="password-card">
    <div class="auth-header">
        <h3>Account Security</h3>
    </div>

    <div class="password-toggle" id="passwordToggle">
        <span>Change Password</span>
        <span class="password-toggle-icon">▼</span>
    </div>

    <form action="ChangePasswordServlet" method="post" class="password-form" id="change-password-form">
        <div class="input-group">
            <label for="currentPassword">Current Password</label>
            <input type="password" id="currentPassword" name="currentPassword" required>
        </div>

        <div class="input-group">
            <label for="newPassword">New Password</label>
            <input type="password" id="newPassword" name="newPassword" required>
            <div id="password-strength" class="password-strength">
                <div class="strength-meter">
                    <div class="strength-meter-fill"></div>
                </div>
                <p class="strength-text">Password strength: Weak</p>
            </div>
        </div>

        <div class="input-group">
            <label for="confirmPassword">Confirm New Password</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
        </div>

        <button type="submit" class="btn btn-primary">Update Password</button>
    </form>
</div>

<footer>
    <p>&copy; 2025 BC Wellness</p>
    <div class="footer-links">
        <a href="#">Privacy Policy</a>
        <a href="#">Terms of Service</a>
        <a href="#">Contact Us</a>
    </div>
</footer>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const form = document.getElementById('appointment-form');
        const confirmationCard = document.getElementById('confirmation-card');
        const newBookingBtn = document.getElementById('new-booking-btn');

        // Format date for display
        function formatDate(dateString) {
            const date = new Date(dateString);
            const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
            return date.toLocaleDateString('en-US', options);
        }

        // Handle form submission
        form.addEventListener('submit', function(e) {
            e.preventDefault();

            // Get form values
            const date = document.getElementById('appointment-date').value;
            const time = document.getElementById('appointment-time').value;
            const concern = document.getElementById('concern-type').options[document.getElementById('concern-type').selectedIndex].text;
            const sessionType = document.getElementById('session-type').options[document.getElementById('session-type').selectedIndex].text;

            // Set confirmation details
            document.getElementById('conf-date').textContent = formatDate(date);
            document.getElementById('conf-time').textContent = time;
            document.getElementById('conf-concern').textContent = concern;
            document.getElementById('conf-type').textContent = sessionType;

            // Determine location based on session type
            const location = sessionType === 'In-Person' ?
                'Wellness Center, Room 205' :
                'Video/Phone link will be emailed to you';
            document.getElementById('conf-location').textContent = location;

            // Show confirmation
            form.closest('.booking-card').style.display = 'none';
            confirmationCard.style.display = 'block';
        });

        // Handle new booking button
        newBookingBtn.addEventListener('click', function() {
            confirmationCard.style.display = 'none';
            form.closest('.booking-card').style.display = 'block';
            form.reset();
        });

        // Set min date to tomorrow
        const today = new Date();
        const tomorrow = new Date(today);
        tomorrow.setDate(today.getDate() + 1);

        const dd = String(tomorrow.getDate()).padStart(2, '0');
        const mm = String(tomorrow.getMonth() + 1).padStart(2, '0');
        const yyyy = tomorrow.getFullYear();

        document.getElementById('appointment-date').min = `${yyyy}-${mm}-${dd}`;

        // NEW PASSWORD UPDATE FUNCTIONALITY
        const passwordToggle = document.getElementById('passwordToggle');
        const passwordForm = document.getElementById('change-password-form');
        const toggleIcon = document.querySelector('.password-toggle-icon');

        passwordToggle.addEventListener('click', function() {
            passwordForm.classList.toggle('expanded');
            toggleIcon.textContent = passwordForm.classList.contains('expanded') ? '▲' : '▼';
        });

        // Password strength meter for update password (same as register)
        const newPasswordInput = document.getElementById('newPassword');
        const strengthMeter = document.querySelector('.strength-meter-fill');
        const strengthText = document.querySelector('.strength-text');
        const passwordStrength = document.getElementById('password-strength');

        newPasswordInput.addEventListener('input', function() {
            const password = newPasswordInput.value;
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

        // Password validation
        document.getElementById('change-password-form').addEventListener('submit', function(e) {
            const newPass = document.getElementById('newPassword').value;
            const confirmPass = document.getElementById('confirmPassword').value;

            if (newPass !== confirmPass) {
                e.preventDefault();
                alert('New passwords do not match!');
                document.getElementById('confirmPassword').focus();
            }
        });
    });
</script>
</body>
</html>