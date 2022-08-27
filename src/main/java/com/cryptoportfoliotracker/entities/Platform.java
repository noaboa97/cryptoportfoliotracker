package com.cryptoportfoliotracker.entities;

import com.cryptoportfoliotracker.repository.TransactionRepository;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

//@Entity
public class Platform {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //@NotEmpty
    private String name;
    private BigDecimal investedBalance; // Methode to calc it in the manager of the platform
    private BigDecimal CurrentBalance; // Methode to calc it?
/*
    public BigDecimal investedBalance() {

        TransactionRepository T = new TransactionRepository();

        T.getTransactionList();

        BigDecimal Balance = new BigDecimal(54);

        return Balance;


    }*/

    public Platform(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getInvestedBalance() {
        return investedBalance;
    }

    public void setInvestedBalance(BigDecimal investedBalance) {
        this.investedBalance = investedBalance;
    }

    public BigDecimal getCurrentBalance() {
        return CurrentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        CurrentBalance = currentBalance;
    }
}
