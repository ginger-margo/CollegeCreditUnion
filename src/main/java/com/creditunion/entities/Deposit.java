package com.creditunion.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Deposit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    private double amount; 
    private String depositDate; 

    // add relationship
    // @ManyToOne
    // private Loan loan; // Link to the Loan entity

    // Constructors
    public Deposit() {
    }

    public Deposit(double amount, String depositDate) {
        this.amount = amount;
        this.depositDate = depositDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(String depositDate) {
        this.depositDate = depositDate;
    }
    
}
