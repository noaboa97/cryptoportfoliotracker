package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.Asset;

import java.util.ArrayList;

public class AssetRepository {    
    
    private ArrayList<Asset> AssetList;

    public AssetRepository(ArrayList<Asset> List) {
        AssetList = List;
    }

    public AssetRepository() {
    }

    public ArrayList<Asset> getAssetList() {
        return AssetList;
    }

    public void addAsset(Asset Asset) {
        AssetList.add(Asset);
    }

}