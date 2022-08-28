package com.cryptoportfoliotracker.logic;

import com.cryptoportfoliotracker.entities.Asset;
import com.cryptoportfoliotracker.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetManager {
    @Autowired
    private final AssetRepository assetRepository;

    public AssetManager(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<Asset> findAllAssets() {
        return assetRepository.findAll();
    }
}
