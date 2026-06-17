Splitwise LLD

A simplified implementation of Splitwise built in Java to practice Low-Level Design concepts.

Features Implemented

* Add Users
* Create Expenses
* Equal Expense Split
* Expense Repository
* Balance Repository
* Balance Tracking

Design Patterns Used

Strategy Pattern
Used for supporting multiple expense splitting algorithms.

Current implementation:
* EqualExpenseSplitter

Planned:

* ExactExpenseSplitter
* PercentageExpenseSplitter

Repository Pattern

Repositories abstract storage concerns.

Current implementations:

* InMemoryExpenseRepository
* InMemoryBalanceRepository

Example

A pays ₹900 for A, B and C

Equal Split:

A -> ₹300
B -> ₹300
C -> ₹300

Balances:

B owes A ₹300
C owes A ₹300

Balance Netting

The system maintains net balances between users instead of storing redundant opposing debts.

Example:

Before Netting:
A pays ₹600
B owes A ₹300

Later:
B pays ₹400
A owes B ₹200

Naive Storage:

* B owes A ₹300
* A owes B ₹200

Netted Result:

* B owes A ₹100

This reduces redundant balance entries and keeps the debt graph simplified.

Future Enhancements

* Percentage Split
* Group Support
* Settle Up
* Transaction History
* Persistent Storage
* REST APIs

Learning Outcomes
``
* Object Modeling
* Separation of Concerns
* Strategy Pattern
* Repository Pattern
* Dependency Injection
* Service Layer Design