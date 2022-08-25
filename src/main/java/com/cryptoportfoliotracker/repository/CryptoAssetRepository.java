package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.CryptoAsset;


import java.util.ArrayList;
import java.util.List;

public class CryptoAssetRepository {

    private List<CryptoAsset> CryptoAssetList = new ArrayList<>();

    public CryptoAssetRepository(List<CryptoAsset> List) {
        this.CryptoAssetList = List;
    }

    public CryptoAssetRepository() {
    }

    public List<CryptoAsset> getCryptoAssetList() {
        return this.CryptoAssetList;
    }

    public void addCryptoAsset(CryptoAsset CryptoAsset) {
        this.CryptoAssetList.add(CryptoAsset);
    }
    
}
