package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.CryptoAsset;
import com.cryptoportfoliotracker.entities.FiatAsset;
import com.cryptoportfoliotracker.entities.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FiatAssetRepository extends JpaRepository<FiatAsset, Long> {

    @Query("select a from Asset a " +
            "where a.fullname = :searchTerm")
    List<FiatAsset> search(@Param("searchTerm") String searchTerm);

    @Query("select a from Asset a " +
            "where a.platform = :searchTerm")
    List<FiatAsset> findAllAssetsOfPlatform(@Param("searchTerm") Platform searchTerm);

}
