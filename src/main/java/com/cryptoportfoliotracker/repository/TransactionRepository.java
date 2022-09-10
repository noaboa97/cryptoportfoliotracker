package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.Asset;
import com.cryptoportfoliotracker.entities.CryptoAsset;
import com.cryptoportfoliotracker.entities.Platform;
import com.cryptoportfoliotracker.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository to create, read, update and delete transactions to the database
 *
 * @author Noah Li Wan Po
 * @version 1.0
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * JPQL Query to find all transaction by source asset
     *
     * @param searchTerm Asset to search for
     * @return Transaction
     * Returns a list with all transaction by source asset
     */
    @Query("select t from Transaction t " +
            "where t.srcAsset = :searchTerm")
    List<Transaction> findBySrcAsset(@Param("searchTerm") Asset searchTerm);

    /**
     * JPQL Query to find all transaction by destination asset
     *
     * @param searchTerm Crypto asset to search for
     * @return Transaction
     * Returns a list with all transaction by destination asset
     */
    @Query("select t from Transaction t " +
            "where t.destAsset = :searchTerm")
    List<Transaction> findByDestAsset(@Param("searchTerm") CryptoAsset searchTerm);

    /**
     * JPQL Query to find all transaction by source platform
     *
     * @param searchTerm Platform to search for
     * @return Transaction
     * Returns a list with all transaction by source platform
     */
    @Query("select t from Transaction t " +
            "where t.srcPlatform = :searchTerm")
    List<Transaction> findBySrcPlatform(@Param("searchTerm") Platform searchTerm);

    /**
     * JPQL Query to find all transaction by destination platform
     *
     * @param searchTerm Platform to search for
     * @return Transaction
     * Returns a list with all transaction by destination platform
     */
    @Query("select t from Transaction t " +
            "where t.destPlatform = :searchTerm")
    List<Transaction> findByDestPlatform(@Param("searchTerm") Platform searchTerm);

    /**
     * JPQL Query to search for the date and time or notes
     *
     * @return Transaction
     * Returns a list with transactions
     */
    @Query("select t from Transaction t " +
            "where lower(t.dateAndTime) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(t.notes) like lower(concat('%', :searchTerm, '%'))")
    List<Transaction> search(@Param("searchTerm") String searchTerm);

}
