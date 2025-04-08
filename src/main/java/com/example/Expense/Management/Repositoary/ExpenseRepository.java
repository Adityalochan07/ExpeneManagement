package com.example.Expense.Management.Repositoary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Expense.Management.Entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
