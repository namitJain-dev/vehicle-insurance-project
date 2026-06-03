# 🚗 Vehicle Insurance Management System

## 📌 Overview
The **Vehicle Insurance Management System (VIMS)** is a modular web application designed to manage vehicle insurance policies, claims, payments, and customer interactions efficiently.

It follows the **MVC (Model-View-Controller)** architecture and it supports Java (Spring MVC)
---

## 🎯 Features
- Policy creation, update, and renewal  
- Claim submission and approval workflow  
- Payment handling (simulated)  
- Customer support ticket management  
- Role-based authentication & authorization  

---

## 🏗️ System Architecture

```
Client (UI)
   ↓
Controller Layer
   ↓
Service Layer (Business Logic)
   ↓
Repository / ORM
   ↓
Database (MySQL / SQL Server)
```

---

## 🔄 Application Flow

### 🧑‍💼 User Flow
1. Register / Login
2. Create or view policy
3. Make payment
4. Raise claim
5. Track claim status
6. Raise support ticket

### 🛠️ Admin / Agent Flow
1. Login
2. Manage policies
3. Approve/reject claims
4. Resolve support tickets

---

## 🧩 Modules

### 1. Policy Management
- Create, update, view, delete policies

### 2. Claim Management
- Submit claims
- Approve/reject
- Track status

### 3. Payment Management
- Make payment
- Track transactions

### 4. Customer Support
- Create and resolve tickets

### 5. Authentication & Authorization
- User registration/login
- Role-based access control

---

## 🗄️ Database

Main tables:
- User
- Policy
- Claim
- Payment
- SupportTicket
- Vehicle

Example:
```
CREATE TABLE Policy (
  policyId INT PRIMARY KEY AUTO_INCREMENT,
  policyNumber VARCHAR(50),
  policyholderId INT,
  vehicleId INT,
  coverageAmount DECIMAL(10,2),
  policyStatus ENUM('ACTIVE','INACTIVE','EXPIRED'),
  createdDate DATE
);
```

---

## 🧪 Tech Stack
- Backend: Spring MVC / ASP.NET Core MVC
- Database: MySQL / SQL Server
- ORM: Hibernate / Entity Framework
- Server: Tomcat / Kestrel

---

## ⚙️ Setup

### Prerequisites
- JDK 17 or .NET SDK 7
- MySQL or SQL Server
- Git

### Installation
```
git clone <repo-url>
```

Configure DB in:
- application.properties (Java)

Run:
```
# Java
mvn spring-boot:run
```

---


---

## 👨‍💻 Author
---

## 📄 License
For academic/demo use.
