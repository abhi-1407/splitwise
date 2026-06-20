# Splitwise LLD

A simplified implementation of Splitwise built in Java to practice Low-Level Design concepts, object-oriented design, design patterns, and backend architecture.

---

## Features Implemented

### User Management
- Register Users
- User Repository
- User Validation

### Expense Management
- Create Expenses
- Equal Split
- Exact Split
- Percentage Split

### Group Management
- Create Groups
- Add Members
- Remove Members
- Group Expense Validation

### Balance Management
- Balance Tracking
- Balance Netting
- Balance Settlement

### Debt Simplification
- Compute net balances across all users
- Simplify debt graph by removing intermediate transactions
- Generate optimized settlements between debtors and creditors

---

## Design Patterns Used

### Strategy Pattern

Used for supporting multiple expense splitting algorithms.

Current implementations:

- EqualExpenseSplitter
- ExactExpenseSplitter
- PercentageExpenseSplitter

### Repository Pattern

Repositories abstract storage concerns.

Current implementations:

- InMemoryUserRepository
- InMemoryExpenseRepository
- InMemoryBalanceRepository
- InMemoryGroupRepository

### Service Layer Pattern

Business logic is encapsulated within services.

- UserService
- ExpenseService
- BalanceService
- GroupService
- DebtSimplificationService

---

## Balance Netting

The system maintains net balances between users instead of storing redundant opposing debts.

### Example

Before Netting:

Rahul owes Abhilash ₹500

Abhilash owes Rahul ₹300

Stored Result:

Rahul owes Abhilash ₹200

This keeps the debt graph minimal and avoids duplicate debt relationships.

---

## Debt Simplification

The system can simplify debt chains by calculating net balances and generating optimized settlements.

### Example

Original Debts

A owes B ₹100

B owes C ₹100

Simplified Result

A owes C ₹100

Intermediate transactions are removed automatically.

---

### Example Output

Net Balances

Abhilash = -800

Mirang = -600

Rahul = +250

Abhishek = +500

Harinder = +50

Sahil = +600

Simplified Settlements

Abhilash → Sahil ₹600

Abhilash → Rahul ₹200

Mirang → Abhishek ₹500

Mirang → Harinder ₹50

Mirang → Rahul ₹50
---

## Future Enhancements

### Backend

- Spring Boot Migration
- PostgreSQL Persistence
- REST APIs
- Swagger/OpenAPI Documentation
- JWT Authentication
- Docker Support

### Product Features

- Expense History
- Settlement History
- Group Specific Settlements
- Notifications
- Recurring Expenses

### Engineering

- Thread Safe Balance Updates
- Unit Tests
- Integration Tests

---

## Learning Outcomes

- Object-Oriented Design
- SOLID Principles
- Strategy Pattern
- Repository Pattern
- Service Layer Design
- Debt Simplification Algorithms
- Balance Netting
- Exception Handling
- Domain Modeling