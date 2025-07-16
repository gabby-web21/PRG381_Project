# BC Student Wellness Management System

A Java-based web application for managing student wellness registrations and logins.

---

## Project Structure

- **Backend:** Java Servlets (Maven project)
- **Frontend:** JSP (JavaServer Pages)
- **Database:** PostgreSQL
- **Server:** Apache Tomcat (Tested on 9.0.x)

---

## Features

- Student registration (with validation & password hashing using BCrypt)
- Login with session tracking
- Logout functionality
- Basic dashboard for authenticated users

---

## How to Run the Project

### 1. Requirements

- Java 17+
- Apache Tomcat 9+
- PostgreSQL 13+ (or any version)
- Maven
- Any IDE (e.g. IntelliJ Community (what we used))

---

### 2. Setup Steps

#### A. Configure Database Connection
Edit the DBUtil.java file to match your PostgreSQL credentials:

```
String url = "jdbc:postgresql://localhost:5432/wellness";
String username = "postgres"; // your DB username
String password = "your_password"; // your DB password

```
No need to manually create the users table — it's handled automatically when a user registers.


---

#### B. Build the Project
From the root directory (where pom.xml is located), open a terminal and run:

```
mvn clean package
```
This will generate a .war file (e.g., PRGPROJTEST.war) in the target/ directory.



---

#### C. Deploy to Apache Tomcat
Copy the .war file to:

```
[Your_Tomcat_Directory]/webapps/
```
Start the Tomcat server.
Open your browser and go to:

```
http://localhost:8081/PRGPROJTEST/
```
Adjust the port if you’ve configured Tomcat differently.


3. Open your browser and go to:

```
http://localhost:8080/PRGPROJTEST/
```

---

## Login Credentials

If no user exists, register using the `register.jsp` form.

Otherwise, use:

- **Email:** test@example.com
- **Password:** (the password you added via SQL)

---

## Testing Pages

- `/index.jsp`: Home page
- `/register.jsp`: Register new user
- `/login.jsp`: Login form
- `/dashboard.jsp`: Protected dashboard (only visible after login)
- `/logout`: Logout servlet (invalidates session)

---

## Common Errors & Fixes

| Error | Fix |
|-------|-----|
| "No suitable driver" | Ensure PostgreSQL JDBC driver is in your `pom.xml` |
| HTTP 404 on `/login` or `/register` | Check your `web.xml` servlet mappings |
| Server error (HTTP 500) | Verify DB connection and correct credentials in `DBUtil.java` |

---

## Credits
Done By: Gabriella Petersen & Luqmaan Slarmie
