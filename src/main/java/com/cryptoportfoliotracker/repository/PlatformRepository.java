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
 * Repository to create, read, update and delete platform to the database
 *
 * @author Noah Li Wan Po
 * @version 1.0
 */
@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {
    /**
     * JPQL Query to search for the name of the platform
     *
     * @return Platform
     *         Returns a list with a fiat asset
     */
    @Query("select p from Platform p " +
            "where p.name = :searchTerm")
    List<Platform> search(@Param("searchTerm") String searchTerm);

    /**
     * JPQL Query to find all crypto platforms
     *
     * @return Platform
     *         Returns a list with all crypto platforms
     */
    @Query("select p from Platform p " +
            "where p.isFiatPlatform = :searchTerm")
    List<Platform> findAllCryptoPlatforms(@Param("searchTerm") boolean searchTerm);
}