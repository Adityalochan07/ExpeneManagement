package com.example.Expense.Management.Service;



import com.example.Expense.Management.Entity.ListExpense;
import com.example.Expense.Management.Exceptions.ResourceNotFoundException;
import com.example.Expense.Management.Repositoary.ListExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class ListExpenseService {

    private final ListExpenseRepository listExpenseRepository;

    // Get all list expenses
    public List<ListExpense> getAllListExpenses() {
        return listExpenseRepository.findAll();
    }

    // Get a list expense by ID
    public ListExpense getListExpenseById(Long id) {
        return listExpenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ListExpense not found with id: " + id));
    }

    // Save a new list expense
    public ListExpense saveListExpense(ListExpense listExpense) {
        return listExpenseRepository.save(listExpense);
    }

    // Update an existing list expense
    public ListExpense updateListExpense(Long id, ListExpense listExpenseDetails) {
        ListExpense existingListExpense = listExpenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ListExpense not found with id: " + id));

        // Set the updated fields
        existingListExpense.setExpenseTitle(listExpenseDetails.getExpenseTitle());
        existingListExpense.setAmount(listExpenseDetails.getAmount());
        existingListExpense.setTags(listExpenseDetails.getTags());
        existingListExpense.setDocument(listExpenseDetails.getDocument());

        // Save the updated list expense
        return listExpenseRepository.save(existingListExpense);
    }

    // Delete a list expense by ID
    public void deleteListExpense(Long id) {
        if (!listExpenseRepository.existsById(id)) {
            throw new ResourceNotFoundException("ListExpense not found with id: " + id);
        }
        listExpenseRepository.deleteById(id);
    }
}

