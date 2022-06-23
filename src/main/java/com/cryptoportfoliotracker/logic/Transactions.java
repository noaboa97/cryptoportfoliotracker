package com.cryptoportfoliotracker.logic;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.Entity;
import java.util.ArrayList;


public class Transactions {

    private ArrayList<Transaction> Transactions;

    public Transactions(ArrayList<Transaction> List) {
        Transactions = List;
    }

    public Transactions() {
    }

    public ArrayList<Transaction> getTransactions() {
        return Transactions;
    }

    public void addTransaction(Transaction Transaction) {
        Transactions.add(Transaction);
    }
}
