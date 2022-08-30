package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
/*
    @Query("select t from Transaction t " +
            "where lower(t.dateandtime) like lower(concat('%', :searchTerm, '%')) ")


    List<Transaction> search(@Param("searchTerm") String searchTerm);
*/
}
