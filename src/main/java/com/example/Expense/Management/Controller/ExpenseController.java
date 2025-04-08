package com.example.Expense.Management.Controller;



import com.example.Expense.Management.Entity.Expense;
import com.example.Expense.Management.Entity.ListExpense;
import com.example.Expense.Management.Exceptions.ResourceNotFoundException;
import com.example.Expense.Management.Service.ExpenseService;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Get all expenses
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    // Get an expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Optional<Expense> expense = expenseService.getExpenseById(id);
        return expense.map(ResponseEntity::ok)
                      .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));
    }

    // Create a new expense
    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        Expense savedExpense = expenseService.saveExpense(expense);
        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    // Update an existing expense
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expenseDetails) {
        Expense updatedExpense = expenseService.updateExpense(id, expenseDetails);
        return ResponseEntity.ok(updatedExpense);
    }

    // Delete an expense by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
    ////
    @PostMapping("/user/{userId}")
    public ResponseEntity<Expense> addExpenseToUser(@PathVariable Long userId, @RequestBody Expense expense) {
        Expense savedExpense = expenseService.addExpenseToUser(userId, expense);
        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    ////
    @PostMapping("/user/{userId}/expense/{expenseId}")
    public ResponseEntity<Void> addListExpensesToUserExpense(@PathVariable Long userId, @PathVariable Long expenseId, @RequestBody List<ListExpense> listExpenses) {
        expenseService.addListExpensesToUserExpense(userId, expenseId, listExpenses);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

