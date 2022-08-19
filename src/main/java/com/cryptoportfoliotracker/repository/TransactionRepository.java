package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.Transaction;

import java.util.ArrayList;


public class TransactionRepository {

    private ArrayList<Transaction> TransactionList;

    public TransactionRepository(ArrayList<Transaction> List) {
        TransactionList = List;
    }

    public TransactionRepository() {
    }

    public ArrayList<Transaction> getTransactionList() {
        return TransactionList;
    }

    public void addTransaction(Transaction Transaction) {
        TransactionList.add(Transaction);
    }
}
