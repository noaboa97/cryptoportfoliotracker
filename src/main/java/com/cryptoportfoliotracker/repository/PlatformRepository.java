package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.Asset;
import com.cryptoportfoliotracker.entities.CryptoAsset;
import com.cryptoportfoliotracker.entities.Platform;
import com.cryptoportfoliotracker.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {

    @Query("select p from Platform p " +
            "where p.name = :searchTerm")
    List<Platform> search(@Param("searchTerm") String searchTerm);

    /*
    @Query("select p from Platform p " +
            "where p.id = :searchTerm")
    List<Platform> getId(@Param("searchTerm") Long id);
*/

    @Query("select p from Platform p " +
            "where p.isFiatPlatform = :searchTerm")
    List<Platform> findAllCryptoPlatforms(@Param("searchTerm") boolean searchTerm);
}