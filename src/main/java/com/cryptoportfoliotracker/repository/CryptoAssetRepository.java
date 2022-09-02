package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.CryptoAsset;
import com.cryptoportfoliotracker.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CryptoAssetRepository extends JpaRepository<CryptoAsset, Long> {

    @Query("select a from Asset a " +
            "where a.fullname = :searchTerm")
    List<CryptoAsset> search(@Param("searchTerm") String searchTerm);

}
