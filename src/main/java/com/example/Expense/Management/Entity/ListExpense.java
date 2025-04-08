package com.example.Expense.Management.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "List_Expense")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ListExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_listid")
    private Long expenseListId;

    @Column(name = "expense_title")
    private String expenseTitle;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "tags")
    private String tags;

    @Column(name = "document")
    private String document;

   

    @ManyToOne
    @JoinColumn(name = "expense_id", referencedColumnName = "expense_id")
    @JsonIgnore
    private Expense expense;

    // Getters and setters
    // ...
}

