package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.CryptoAsset;
import com.cryptoportfoliotracker.entities.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository to create, read, update and delete crypto assets to the database
 *
 * @author Noah Li Wan Po
 * @version 1.0
 */
@Repository
public interface CryptoAssetRepository extends JpaRepository<CryptoAsset, Long> {

    /**
     * JPQL Query to search for the name of the crypto asset
     *
     * @return CryptoAsset
     * Returns a list with a crypto asset
     */
    @Query("select a from Asset a " +
            "where a.fullName = :searchTerm")
    List<CryptoAsset> search(@Param("searchTerm") String searchTerm);

    /**
     * JPQL Query to find all crypto assets of a platform
     *
     * @return CryptoAsset
     * Returns a list with all crypto assets of a platform
     */
    @Query("select a from Asset a " +
            "where a.platform = :searchTerm")
    List<CryptoAsset> findAllCryptoAssetsOfPlatform(@Param("searchTerm") Platform searchTerm);


}
