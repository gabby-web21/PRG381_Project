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

#### A. Database Setup (PostgreSQL)

1. Open `psql` or PgAdmin and run the following SQL to create the database and table:

```sql
CREATE DATABASE wellness;

\c wellness

CREATE TABLE users (
    student_number VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100),
    surname VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(15),
    password TEXT
);
```

2. (Optional) Insert a sample user:

```sql
INSERT INTO users (student_number, name, surname, email, phone, password)
VALUES ('600001', 'Test', 'User', 'test@example.com', '0123456789', 'your_hashed_password_here');
```

---

#### B. Update DB Credentials

Open the `DBUtil.java` file and make sure your credentials match your local PostgreSQL setup:

```java
String url = "jdbc:postgresql://localhost:5432/wellness";
String username = "postgres"; //your DB user
String password = "your_password"; // your DB password
```

---

#### C. Build the Project

In the root folder (where `pom.xml` is), run:

```bash
mvn clean package
```

This will generate a `.war` file in the `target/` folder.

---

#### D. Deploy to Apache Tomcat

1. Copy the `.war` file (e.g., `PRGPROJTEST.war`) to:

```
[Tomcat Folder]/webapps/
```

2. Start Tomcat.

3. Open your browser and go to:

```
http://localhost:8081/PRGPROJTEST/
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
