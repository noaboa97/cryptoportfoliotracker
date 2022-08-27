package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class TransactionRepository {


    private List<Transaction> TransactionList = new ArrayList<>();

    public TransactionRepository(ArrayList<Transaction> List) {
        this.TransactionList = List;
    }

    public TransactionRepository() {
    }

    public List<Transaction> getTransactionList() {
        return this.TransactionList;
    }

    public void addTransaction(Transaction Transaction) {
        this.TransactionList.add(Transaction);
    }
}
