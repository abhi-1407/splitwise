Splitwise

A Splitwise clone built to practice backend engineering, low-level design, object-oriented design, design patterns, and Spring Boot development.

The project started as a pure Java LLD implementation and is currently being migrated to Spring Boot with REST APIs.

⸻

Features Implemented

User Management

* Create User
* Fetch User by ID
* User Validation
* Duplicate User Detection

Group Management

* Create Group
* Fetch Group
* Fetch All Groups
* Add Members
* Remove Members
* Group Membership Validation

Expense Management

* Create Expenses
* Equal Split
* Exact Split
* Percentage Split

Balance Management

* Balance Tracking
* Balance Netting
* Balance Settlement

Debt Simplification

* Compute Net Balances
* Identify Creditors and Debtors
* Simplify Debt Graph
* Generate Optimized Settlements

⸻

REST APIs Implemented

User APIs

POST /users
GET /users/{userId}

Group APIs

POST /groups
GET /groups
GET /groups/{groupId}
GET /groups/{groupId}/members
POST /groups/members/{groupId}/{userId}
DELETE /groups/members/{groupId}/{userId}

⸻

Current Architecture

Controller
↓
DTO
↓
Service
↓
Repository
↓
In-Memory Storage

The current implementation uses in-memory repositories to focus on business logic and API design before introducing persistent storage.

⸻

Design Patterns Used

Strategy Pattern

Used for supporting multiple expense splitting algorithms.

Implementations:

* EqualExpenseSplitter
* ExactExpenseSplitter
* PercentageExpenseSplitter

Repository Pattern

Repositories abstract storage concerns from business logic.

Implementations:

* InMemoryUserRepository
* InMemoryExpenseRepository
* InMemoryBalanceRepository
* InMemoryGroupRepository

Service Layer Pattern

Business logic is encapsulated within dedicated services.

Services:

* UserService
* GroupService
* ExpenseService
* BalanceService
* DebtSimplificationService

DTO Pattern

Request and Response DTOs are used to separate API contracts from domain entities.

Examples:

* CreateUserRequest
* UserResponse
* CreateGroupRequest

Dependency Injection

Spring Boot manages object creation and dependency wiring through constructor injection.

⸻

Balance Netting

The system stores only net balances between users.

Example

Before Netting

Rahul owes Abhilash ₹500

Abhilash owes Rahul ₹300

Stored Result

Rahul owes Abhilash ₹200

This avoids maintaining duplicate debt relationships.

⸻

Debt Simplification

The system can simplify debt chains by calculating net balances and generating optimized settlements.

Example

Original Debts

A owes B ₹100

B owes C ₹100

Simplified Result

A owes C ₹100

Intermediate transactions are eliminated.

⸻

Example Output

Current Balances

Rahul owes Abhilash ₹100

Abhishek owes Abhilash ₹100

Mirang owes Abhilash ₹300

Abhishek owes Rahul ₹200

Mirang owes Rahul ₹200

Simplified Settlements

Mirang → Abhilash ₹400

Abhishek → Rahul ₹300

Mirang → Rahul ₹100

⸻

Current Limitations

The current implementation intentionally focuses on core business logic and API design.

Limitations:

* Balances are maintained globally and not per group.
* Data is stored entirely in-memory.
* No persistent database layer.
* No expense history support.
* No settlement history support.
* No authentication or authorization.
* Balance updates are not thread-safe.
* Expense creation and settlement are not atomic operations.

⸻

Tech Stack

* Java 21
* Spring Boot
* Maven
* Lombok
* Jakarta Validation
* REST APIs

⸻

Future Enhancements

Backend

* PostgreSQL Integration
* Spring Data JPA
* Swagger/OpenAPI Documentation
* JWT Authentication
* Docker Support
* Flyway Database Migrations

Product Features

* Expense History
* Settlement History
* Group Specific Balances
* Group Specific Debt Simplification
* Notifications
* Recurring Expenses

Engineering

* Thread-Safe Balance Updates
* Transaction Management
* Concurrency Handling
* Optimistic Locking
* Pessimistic Locking
* Unit Tests
* Integration Tests

⸻

Learning Outcomes

* Object-Oriented Design
* SOLID Principles
* Spring Boot Fundamentals
* REST API Design
* DTO Pattern
* Dependency Injection
* Strategy Pattern
* Repository Pattern
* Service Layer Design
* Debt Simplification Algorithms
* Balance Netting
* Exception Handling
* Domain Modeling
* Backend Architecture
