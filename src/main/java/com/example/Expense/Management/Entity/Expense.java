package com.example.Expense.Management.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Expense")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private Long expenseId;

    @Column(name = "base_salary")
    private Double baseSalary;

    @Column(name = "saving")
    private Double saving;

    @Column(name = "loan")
    private Double loan;
    @Column(name="current_limit")
    private Double current_limit;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIgnore
    private User user;
    
    

    @ManyToOne
    @JoinColumn(name = "current_expense", referencedColumnName = "expense_listid")
    @JsonIgnore
    private ListExpense currentExpense;


    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
    private Set<ListExpense> expenseList;


    public Expense(Double baseSalary, Double saving, Double loan, Double current_limit, User user,
            ListExpense currentExpense, Set<ListExpense> expenseList) {
        this.baseSalary = baseSalary;
        this.saving = saving;
        this.loan = loan;
        this.current_limit = current_limit;
        this.user = user;
        this.currentExpense = currentExpense;
        this.expenseList = expenseList;
    }


    // Getters and setters
    // ...
}

