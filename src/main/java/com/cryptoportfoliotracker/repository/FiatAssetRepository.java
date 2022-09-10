package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.FiatAsset;
import com.cryptoportfoliotracker.entities.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository to create, read, update and delete fiat assets to the database
 *
 * @author Noah Li Wan Po
 * @version 1.0
 */
public interface FiatAssetRepository extends JpaRepository<FiatAsset, Long> {
    /**
     * JPQL Query to search for the name of the fiat asset
     *
     * @return FiatAsset
     *         Returns a list with a fiat asset
     */
    @Query("select a from Asset a " +
            "where a.fullName = :searchTerm")
    List<FiatAsset> search(@Param("searchTerm") String searchTerm);

    /**
     * JPQL Query to find the standard fiat platform
     *
     * @return FiatAsset
     *         Returns a list with a fiat asset
     */
    @Query("select a from Asset a " +
            "where a.standard = :searchTerm")
    FiatAsset findStandard(@Param("searchTerm") boolean searchTerm);

    /**
     * JPQL Query to find all fiat assets of a platform
     *
     * @return FiatAsset
     *         Returns a list with fiat assets
     */
    @Query("select a from Asset a " +
            "where a.platform = :searchTerm")
    List<FiatAsset> findAllFiatAssetsOfPlatform(@Param("searchTerm") Platform searchTerm);

}
