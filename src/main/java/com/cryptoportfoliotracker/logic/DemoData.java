package com.cryptoportfoliotracker.logic;

import com.cryptoportfoliotracker.entities.*;
import com.cryptoportfoliotracker.repository.*;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringComponent
public class DemoData {

    @Bean
    public CommandLineRunner loadData(AssetRepository assetRepository, CryptoAssetRepository cryptoAssetRepository, PlatformRepository platformRepository, TransactionRepository transactionRepository, FiatAssetRepository fiatAssetRepository) {

        return args -> {

            Platform P = new Platform( "Bitpanda");
            Platform P3 = new Platform( "Crypto.com");
            Platform P4 = new Platform( "Nexo");
            Platform P5 = new Platform( "Kraken");
            Platform P2 = new Platform( "PostFinance", true);
            platformRepository.save(P);
            platformRepository.save(P2);
            platformRepository.save(P3);
            platformRepository.save(P4);
            platformRepository.save(P5);
            CryptoAsset CA = new CryptoAsset("Bitcoin", "BTC", P);
            BigDecimal BD = new BigDecimal("19583.39");
            CA.setCurrentValueFiat(BD);
            cryptoAssetRepository.save(CA);

            FiatAsset a = new FiatAsset("Schweizer Franken", "CHF", P2);
            fiatAssetRepository.save(a);

            Transaction T = new Transaction(LocalDateTime.now(),new BigDecimal("2300"),a,new BigDecimal("0.23"),CA,P2,P,new BigDecimal("0"),a);
            transactionRepository.save(T);

        };
    }

}
