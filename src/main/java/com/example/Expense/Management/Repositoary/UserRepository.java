package com.example.Expense.Management.Repositoary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Expense.Management.Entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
