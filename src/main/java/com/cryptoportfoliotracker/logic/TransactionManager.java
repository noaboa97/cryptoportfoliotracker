package com.cryptoportfoliotracker.logic;

import com.cryptoportfoliotracker.entities.Transaction;
import com.cryptoportfoliotracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionManager {
    //@Autowired
    private TransactionRepository transactionRepository;
    //@Autowired
    public TransactionManager(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    public List<Transaction> findAllTransactions(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {


            return (List<Transaction>) transactionRepository.findAll();
        } else {
            return null; //transactionRepository.search(stringFilter);
        }
    }

    public long countTransactions() {
        return transactionRepository.count();
    }

    public void deleteTransaction(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

    public void saveTransaction(Transaction transaction) {
        if (transaction == null) {


            System.err.println("Transaction is null. Are you sure you have connected your form to the application?");
            return;
        }
        transactionRepository.save(transaction);
    }

}
