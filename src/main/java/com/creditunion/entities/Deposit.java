package com.creditunion.entities;

import javax.persistence.*;

@Entity
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String depositDate;
    private double amount;
    
    @ManyToOne
    private Loan loan; // each deposit associated with a specific loan, useful to filter by loan

    // Empty Constructor
    public Deposit() {
    }

    // Constructor
    public Deposit(int id, String depositDate, double amount) {
        this.id = id;
        this.depositDate = depositDate;
        this.amount = amount;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(String depositDate) {
        this.depositDate = depositDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}
