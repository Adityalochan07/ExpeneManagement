package com.example.Expense.Management.Controller;

    
import com.example.Expense.Management.Entity.ListExpense;
import com.example.Expense.Management.Service.ListExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/list-expenses")
@AllArgsConstructor
public class ListExpenseController {

    private final ListExpenseService listExpenseService;

    // Get all list expenses
    @GetMapping
    public ResponseEntity<List<ListExpense>> getAllListExpenses() {
        List<ListExpense> listExpenses = listExpenseService.getAllListExpenses();
        return ResponseEntity.ok(listExpenses);
    }

    // Get a list expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<ListExpense> getListExpenseById(@PathVariable Long id) {
        ListExpense listExpense = listExpenseService.getListExpenseById(id);
        return ResponseEntity.ok(listExpense);
    }

    // Create a new list expense
    @PostMapping
    public ResponseEntity<ListExpense> createListExpense(@RequestBody ListExpense listExpense) {
        ListExpense savedListExpense = listExpenseService.saveListExpense(listExpense);
        return new ResponseEntity<>(savedListExpense, HttpStatus.CREATED);
    }

    // Update an existing list expense
    @PutMapping("/{id}")
    public ResponseEntity<ListExpense> updateListExpense(@PathVariable Long id, @RequestBody ListExpense listExpenseDetails) {
        ListExpense updatedListExpense = listExpenseService.updateListExpense(id, listExpenseDetails);
        return ResponseEntity.ok(updatedListExpense);
    }

    // Delete a list expense by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListExpense(@PathVariable Long id) {
        listExpenseService.deleteListExpense(id);
        return ResponseEntity.noContent().build();
    }
}

