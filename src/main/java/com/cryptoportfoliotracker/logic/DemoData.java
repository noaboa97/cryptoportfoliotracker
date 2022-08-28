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
import org.springframework.stereotype.Component;


@Component
public class DemoData {

    @Bean
    public CommandLineRunner loadData(AssetRepository assetRepository, CryptoAssetRepository cryptoAssetRepository,
                                      PlatformRepository platformRepository, TransactionRepository transactionRepository) {

        return args -> {
            if (transactionRepository.count() != 0L) {
                return;
            }

            // AssetRepository assetRepository, CryptoAssetRepository cryptoAssetRepository,
            //      PlatformRepository platformRepository, TransactionRepository transactionRepository
            List<Asset> assets = (List<Asset>) assetRepository;

            List<CryptoAsset> cryptoAssets = (List<CryptoAsset>) cryptoAssetRepository;

            List<Platform> platforms = (List<Platform>) platformRepository;

            List<Transaction> transactions = (List<Transaction>) transactionRepository;
            //Testing only

            UUID puuid = UUID.randomUUID();
            UUID p2uuid = UUID.randomUUID();
            UUID cuuid = UUID.randomUUID();
            Platform P = new Platform(puuid, "Bitpanda");
            Platform P2 = new Platform(p2uuid, "PostFinance");
            platforms.add(P);
            platforms.add(P2);
            CryptoAsset CA = new CryptoAsset( cuuid,"Bitcoin", "BTC", P);
            cryptoAssets.add(CA);

            assetRepository.saveAll(assets);
            cryptoAssetRepository.saveAll(cryptoAssets);
            platformRepository.saveAll(platforms);
            transactionRepository.saveAll(transactions);

        };
    }

}
