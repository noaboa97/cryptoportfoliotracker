package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select t from Transaction t " +
            "where lower(t.dateAndTime) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(t.notes) like lower(concat('%', :searchTerm, '%'))")

    List<Transaction> search(@Param("searchTerm") String searchTerm);

}
