package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Repository
public interface AssetRepository extends JpaRepository<Asset, UUID> {
    /*
    @Query("select a from Asset a " +
            "where lower(a.name) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(a.shortname) like lower(concat('%', :searchTerm, '%'))")


    List<Asset> search(@Param("searchTerm") String searchTerm);

*/
}