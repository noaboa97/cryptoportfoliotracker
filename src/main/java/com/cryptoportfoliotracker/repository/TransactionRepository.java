package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.Asset;
import com.cryptoportfoliotracker.entities.CryptoAsset;
import com.cryptoportfoliotracker.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
/*
    @Query("select t from Transaction t " +
            "where lower(t.dateandtime) like lower(concat('%', :searchTerm, '%')) ")


    List<Transaction> search(@Param("searchTerm") String searchTerm);
*/
}
