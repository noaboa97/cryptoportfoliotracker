package com.cryptoportfoliotracker.logic;

import com.cryptoportfoliotracker.entities.CryptoAsset;
import com.cryptoportfoliotracker.repository.AssetRepository;
import com.cryptoportfoliotracker.repository.CryptoAssetRepository;
import com.cryptoportfoliotracker.repository.PlatformRepository;
import com.cryptoportfoliotracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoAssetManager {
    @Autowired
    private final CryptoAssetRepository cryptoAssetRepository;


    public CryptoAssetManager(CryptoAssetRepository cryptoAssetRepository) {
        this.cryptoAssetRepository = cryptoAssetRepository;

    }

    public List<CryptoAsset> findAllCryptoAssets() {
        return cryptoAssetRepository.findAll();
    }
}
