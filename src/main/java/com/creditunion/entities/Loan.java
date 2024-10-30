package com.creditunion.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String description;
    private double amount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Deposit> deposits = new ArrayList<Deposit>();

    // Empty Constructor
    public Loan() {
    }

    // Constructor
    public Loan(int id, String description, double amount, List<Deposit> deposits) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.deposits = deposits;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }
}
