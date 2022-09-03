package com.cryptoportfoliotracker.logic;

import com.cryptoportfoliotracker.entities.CryptoAsset;
import com.cryptoportfoliotracker.entities.Platform;
import com.cryptoportfoliotracker.entities.Transaction;
import com.cryptoportfoliotracker.repository.AssetRepository;
import com.cryptoportfoliotracker.repository.CryptoAssetRepository;
import com.cryptoportfoliotracker.repository.PlatformRepository;
import com.cryptoportfoliotracker.repository.TransactionRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringComponent
public class DemoData {

    @Bean
    public CommandLineRunner loadData(AssetRepository assetRepository, CryptoAssetRepository cryptoAssetRepository, PlatformRepository platformRepository, TransactionRepository transactionRepository) {

        return args -> {

            Platform P = new Platform( "Bitpanda");
            Platform P2 = new Platform( "PostFinance");
            platformRepository.save(P);
            platformRepository.save(P2);
            CryptoAsset CA = new CryptoAsset("Bitcoin", "BTC", P);
            BigDecimal BD = new BigDecimal("19583.39");
            CA.setCurrentValueFiat(BD);
            cryptoAssetRepository.save(CA);
            CryptoAsset CA2 = new CryptoAsset("Schweizer Franken", "CHF", P2);
            cryptoAssetRepository.save(CA2);
            ;
            Transaction T = new Transaction(LocalDateTime.now(),new BigDecimal("2300"),CA2,new BigDecimal("0.23"),CA,P2,P,new BigDecimal("0"),CA2);
            transactionRepository.save(T);

        };
    }

}
