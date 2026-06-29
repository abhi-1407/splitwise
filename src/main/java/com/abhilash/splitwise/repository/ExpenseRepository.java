package com.abhilash.splitwise.repository;

import com.abhilash.splitwise.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, String> {

}