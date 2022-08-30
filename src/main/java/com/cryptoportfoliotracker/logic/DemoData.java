package com.cryptoportfoliotracker.logic;

import com.cryptoportfoliotracker.entities.Asset;
import com.cryptoportfoliotracker.entities.CryptoAsset;
import com.cryptoportfoliotracker.entities.Platform;
import com.cryptoportfoliotracker.entities.Transaction;
import com.cryptoportfoliotracker.repository.AssetRepository;
import com.cryptoportfoliotracker.repository.CryptoAssetRepository;
import com.cryptoportfoliotracker.repository.PlatformRepository;
import com.cryptoportfoliotracker.repository.TransactionRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;


import java.util.List;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringComponent
public class DemoData {

    @Bean
    public CommandLineRunner loadData(AssetRepository assetRepository, CryptoAssetRepository cryptoAssetRepository, PlatformRepository platformRepository, TransactionRepository transactionRepository) {

        return args -> {
            //if (transactionRepository.count() != 0L) {
                //return;
            //}

            // AssetRepository assetRepository, CryptoAssetRepository cryptoAssetRepository,
            /*/      PlatformRepository platformRepository, TransactionRepository transactionRepository
            List<Asset> assets = assetRepository;

            List<CryptoAsset> cryptoAssets = (List<CryptoAsset>) cryptoAssetRepository;

            List<Platform> platforms = (List<Platform>) platformRepository;

            List<Transaction> transactions = (List<Transaction>) transactionRepository;
            //Testing only
*/
            Platform P = new Platform( "Bitpanda");
            Platform P2 = new Platform( "PostFinance");
            platformRepository.save(P);
            platformRepository.save(P2);
            CryptoAsset CA = new CryptoAsset("Bitcoin", "BTC", P);
            cryptoAssetRepository.save(CA);
            CryptoAsset CA2 = new CryptoAsset("Schweizer Franken", "CHF", P2);
            cryptoAssetRepository.save(CA2);

            //System.out.println(cryptoAssetRepository.findAll());

/*
            assetRepository.saveAll(assets);
            cryptoAssetRepository.saveAll(cryptoAssets);
            platformRepository.saveAll(platforms);
            transactionRepository.saveAll(transactions);
*/
        };
    }

}
