package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.Asset;
import com.cryptoportfoliotracker.entities.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository to create, read, update and delete asset to the database
 *
 * @author Noah Li Wan Po
 * @version 1.0
 */
@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    /**
     * JPQL Query to find all assets of a platform
     *
     * @return Asset
     * Returns a list with all assets of a platform
     */
    @Query("select a from Asset a " +
            "where a.platform = :searchTerm")
    List<Asset> findAllAssetsOfPlatform(@Param("searchTerm") Platform searchTerm);

}