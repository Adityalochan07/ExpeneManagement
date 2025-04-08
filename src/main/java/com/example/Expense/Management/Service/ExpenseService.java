package com.example.Expense.Management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Expense.Management.Entity.Expense;
import com.example.Expense.Management.Entity.ListExpense;
import com.example.Expense.Management.Entity.User;
import com.example.Expense.Management.Exceptions.ResourceNotFoundException;
import com.example.Expense.Management.Repositoary.ExpenseRepository;
import com.example.Expense.Management.Repositoary.ListExpenseRepository;

import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
 
@Service
@AllArgsConstructor
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
      private final ListExpenseRepository listExpenseRepository;
    // Get all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Get an expense by ID
    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    // Save a new expense
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    // Update an existing expense
    public Expense updateExpense(Long id, Expense expenseDetails) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));
        
        // Set the updated fields
        existingExpense.setBaseSalary(expenseDetails.getBaseSalary());
        existingExpense.setSaving(expenseDetails.getSaving());
        existingExpense.setLoan(expenseDetails.getLoan());
        
        // Save the updated expense
        return expenseRepository.save(existingExpense);
    }

    // Delete an expense by ID
    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Expense not found with id: " + id);
        }
        expenseRepository.deleteById(id);
    }

    ////////////
   // add expense to particular user
   private final UserService userService;
   public Expense addExpenseToUser(Long userId, Expense expense) {
    User user = userService.getUserById(userId).orElseThrow(()-> new ResourceNotFoundException("Not Found")); // Fetch the user
    expense.setUser(user); // Associate the expense with the user
    return expenseRepository.save(expense); // Save the expense
}

/// add expense to list of expense 
public void addListExpensesToUserExpense(Long userId, Long expenseId, List<ListExpense> listExpenses) {
    User user = userService.getUserById(userId).orElseThrow(()-> new ResourceNotFoundException("Not Found")); // Fetch the user
    Expense expense = expenseRepository.findById(expenseId)
            .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + expenseId)); // Fetch the expense

    // Associate each ListExpense with the given expense
    double totalListofExpense=0.0;
    for (ListExpense listExpense : listExpenses) {
        totalListofExpense=+listExpense.getAmount();
        listExpense.setExpense(expense); // Set the expense on each ListExpense
    }
    double updateSalary=expense.getBaseSalary()-totalListofExpense;
    expense.setCurrent_limit(updateSalary);
    expenseRepository.save(expense);

   

    // Save all ListExpense records
    listExpenseRepository.saveAll(listExpenses);
}



}

