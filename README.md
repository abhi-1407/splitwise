Splitwise LLD

A simplified implementation of Splitwise built in Java to practice Low-Level Design concepts, OOP principles, and design patterns.

Features Implemented

User Management
* Add Users
* User repository
* User Lookup

Expense Management
* Create Expenses
* Expense repository

Expense Splitting Strategies
* Equal Split
* Exact Split
* Percentage Split

Balance Management
* Balance repository
* Balance Tracking
* Balance Netting
* Show User Balances
* Show All Balances

⸻

Design Patterns Used

strategy Pattern
Used to support multiple expense splitting algorithms.

Implemented Strategies:
* EqualExpenseSplitter
* ExactExpenseSplitter
* PercentageExpenseSplitter

This allows new splitting algorithms to be added without modifying existing business logic.

⸻

repository Pattern
Repositories abstract storage concerns from business logic.

Implemented Repositories:
* InMemoryUserRepository
* InMemoryExpenseRepository
* InMemoryBalanceRepository

⸻

Example

Equal Split

Expense:
A pays ₹900 for A, B and C.

Split:
* A -> ₹300
* B -> ₹300
* C -> ₹300

Balances:
* B owes A ₹300
* C owes A ₹300

⸻

Exact Split

Expense:
A pays ₹1000

Split:
* A -> ₹200
* B -> ₹300
* C -> ₹500

Balances:
* B owes A ₹300
* C owes A ₹500

⸻

Percentage Split

Expense:
A pays ₹1000

Percentages:
* A -> 20%
* B -> 30%
* C -> 50%

Calculated Split:
* A -> ₹200
* B -> ₹300
* C -> ₹500

Balances:
* B owes A ₹300
* C owes A ₹500

⸻

Balance Netting
The system maintains net balances between users instead of storing redundant opposing debts.

Example

Expense 1
A pays ₹600

Result:
* B owes A ₹300

Expense 2
B pays ₹400

Result:
* A owes B ₹200

Naive Storage
* B owes A ₹300
* A owes B ₹200

Netted Result
* B owes A ₹100

This keeps the balance graph simplified and avoids storing redundant debt relationships.

Future Enhancements
* Group Support
* Settle Up
* Transaction History
* Persistent Storage
* REST APIs
* Spring Boot Integration

⸻

Learning Outcomes
* Object Modeling
* OOP Principles
* Separation of Concerns
* strategy Pattern
* repository Pattern
* Dependency Injection
* service Layer Design
* Balance Netting Logic
* Extensible System Design