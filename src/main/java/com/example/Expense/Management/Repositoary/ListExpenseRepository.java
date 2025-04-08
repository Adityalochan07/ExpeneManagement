package com.example.Expense.Management.Repositoary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Expense.Management.Entity.ListExpense;

public interface ListExpenseRepository extends JpaRepository<ListExpense, Long> {
    

}
