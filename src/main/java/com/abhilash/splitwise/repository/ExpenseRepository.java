package com.abhilash.splitwise.repository;

import com.abhilash.splitwise.entity.Expense;
import com.abhilash.splitwise.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, String> {

    List<Expense> findByGroup(Group group);

}