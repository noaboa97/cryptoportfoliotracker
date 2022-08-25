package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.Asset;

import java.util.ArrayList;
import java.util.List;

public class AssetRepository {    
    
    private List<Asset> AssetList = new ArrayList<>();

    public AssetRepository(List<Asset> List) {
        this.AssetList = List;
    }

    public AssetRepository() {
    }

    public List<Asset> getAssetList() {
        return this.AssetList;
    }

    public void addAsset(Asset Asset) {
        this.AssetList.add(Asset);
    }

}