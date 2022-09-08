package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select t from Transaction t " +
            "where t.srcAsset = :searchTerm")
    List<Transaction> findBySrcAsset(@Param("searchTerm") Asset searchTerm);

    @Query("select t from Transaction t " +
            "where t.destAsset = :searchTerm")
    List<Transaction> findByDestAsset(@Param("searchTerm") CryptoAsset searchTerm);

    @Query("select t from Transaction t " +
            "where t.srcPlatform = :searchTerm")
    List<Transaction> findBySrcPlatform(@Param("searchTerm") Platform searchTerm);

    @Query("select t from Transaction t " +
            "where t.destPlatform = :searchTerm")
    List<Transaction> findByDestPlatform(@Param("searchTerm") Platform searchTerm);

}
