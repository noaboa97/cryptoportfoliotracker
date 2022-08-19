package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.CryptoAsset;

import java.util.ArrayList;

public class CryptoAssetRepository {

    private ArrayList<CryptoAsset> CryptoAssetList;

    public CryptoAssetRepository(ArrayList<CryptoAsset> List) {
        CryptoAssetList = List;
    }

    public CryptoAssetRepository() {
    }

    public ArrayList<CryptoAsset> getCryptoAssetList() {
        return CryptoAssetList;
    }

    public void addCryptoAsset(CryptoAsset CryptoAsset) {
        CryptoAssetList.add(CryptoAsset);
    }
    
}
