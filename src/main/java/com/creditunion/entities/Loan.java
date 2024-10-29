package com.creditunion.entities;

import javax.persistence.*;

@Entity
public class Loan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    private String description;
    private double amount; 

    // Add relationships if necessary
    // @ManyToOne
    // private Student student; // Link to the Student entity

    // Constructors
    public Loan() {
    }

    public Loan(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
}
