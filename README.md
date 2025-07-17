# BC Student Wellness Management System

A Java-based web application that allows students to register and log in to access mental wellness services.

---

## Project Structure

- **Backend:** Java Servlets (Maven project)
- **Frontend:** JSP (JavaServer Pages)
- **Database:** PostgreSQL
- **Server:** Apache Tomcat (Tested on version 9.0.x)

---

## Features

- Student registration with validation
- Secure password storage using BCrypt
- Login system with session tracking
- Logout functionality
- Authenticated user dashboard

---

## How to Run the Project

### 1. Requirements

- Java 17 or later
- Apache Tomcat 9 or later
- PostgreSQL 13 or later
- Maven
- Any Java IDE (e.g., IntelliJ IDEA Community Edition)

---

### 2. Setup Instructions

#### A. Create the PostgreSQL Database

1. Open your SQL Shell(psql).
2. Create a new database named:

```
wellness
```

3. You can let the application create the necessary tables automatically.  
   However, if you prefer to create the `users` table manually, use this SQL:

```sql
CREATE TABLE IF NOT EXISTS users (
    student_number VARCHAR(10) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20) NOT NULL,
    password TEXT NOT NULL
);
```

> Note: The `appointments` table will also be auto-created in the latest version.

#### B. Configure Database Credentials

Update the `DBUtil.java` file with your PostgreSQL credentials:

```java
String url = "jdbc:postgresql://localhost:5432/wellness";
String username = "postgres"; // your PostgreSQL username
String password = "your_password"; // your PostgreSQL password
```

---

#### C. Build the Project

From the root of the project (where `pom.xml` is located), run the following Maven command:

```bash
mvn clean package
```

This generates a `.war` file (e.g., `PRGPROJTEST.war`) inside the `target/` directory.

---

#### D. Deploy to Tomcat

1. Copy the generated `.war` file to the `webapps/` directory of your Apache Tomcat installation:

```
[Tomcat_Directory]/webapps/
```

2. Start the Tomcat server.

3. Open your browser and go to:

```
http://localhost:8080/PRGPROJTEST/
```

> Adjust the port number if your Tomcat is configured differently.

---

## Login Credentials

You can register a new user using the `register.jsp` page.

To test manually with SQL, insert a user record into the `users` table and use the matching email and password to log in.

---

## Testing Pages

| Page              | Description                              |
|-------------------|------------------------------------------|
| `/index.jsp`      | Home page with navigation                |
| `/register.jsp`   | Student registration form                |
| `/login.jsp`      | Login form                               |
| `/dashboard.jsp`  | User dashboard (after login)             |
| `/logout`         | Logout servlet (clears session)          |

---

## Common Errors & Fixes

| Error Message                   | Fix                                                         |
|--------------------------------|--------------------------------------------------------------|
| "No suitable driver"           | Ensure PostgreSQL JDBC driver is included in `pom.xml`      |
| HTTP 404 on `/login` or `/register` | Check servlet mappings in `web.xml`                   |
| HTTP 500 (Server Error)        | Verify database connection and credentials in `DBUtil.java` |

---

## Credits

Project developed by:

- Gabriella Petersen  
- Luqmaan Slarmie
