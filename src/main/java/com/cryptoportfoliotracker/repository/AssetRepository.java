package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to create, read, update and delete asset to the database
 *
 * @author Noah Li Wan Po
 * @version 1.0
 */
@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

}