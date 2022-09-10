package com.cryptoportfoliotracker.logic;

import com.cryptoportfoliotracker.entities.CryptoAsset;
import com.cryptoportfoliotracker.entities.FiatAsset;
import com.cryptoportfoliotracker.entities.Platform;
import com.cryptoportfoliotracker.entities.Transaction;
import com.cryptoportfoliotracker.repository.*;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Class which loads demo data into the h2 databse
 *
 * @author Noah Li Wan Po
 * @version 1.0
 */
@SpringComponent
public class DemoData {

    @Bean
    public CommandLineRunner loadData(AssetRepository assetRepository, CryptoAssetRepository cryptoAssetRepository, PlatformRepository platformRepository, TransactionRepository transactionRepository, FiatAssetRepository fiatAssetRepository) {

        return args -> {

            // Creates the platform objects 
            Platform bitpanda = new Platform("Bitpanda");
            Platform cCom = new Platform("Crypto.com");
            Platform nexo = new Platform("Nexo");
            Platform celcius = new Platform("Celcius");
            Platform swissborg = new Platform("Swissborg");
            Platform deFiWallet = new Platform("Crypto.com DeFi Wallet");


            Platform postFinance = new Platform("PostFinance", true);

            // Saves the platform objects to the repository
            platformRepository.save(bitpanda);
            platformRepository.save(postFinance);
            platformRepository.save(cCom);
            platformRepository.save(nexo);
            platformRepository.save(celcius);
            platformRepository.save(swissborg);
            platformRepository.save(deFiWallet);


            // Creates the crypto assets objects
            CryptoAsset bitcoinBitpanda = new CryptoAsset("Bitcoin", "BTC", bitpanda);
            CryptoAsset bitcoinNexo = new CryptoAsset("Bitcoin", "BTC", nexo);
            CryptoAsset daiDeFi = new CryptoAsset("Dai", "DAI", deFiWallet);
            CryptoAsset ethDeFi = new CryptoAsset("Ethereum", "ETH", deFiWallet);
            CryptoAsset chilizSwissborg = new CryptoAsset("Chiliz", "CHZ", swissborg);
            CryptoAsset uniswapSwissborg = new CryptoAsset("Uniswap", "UNI", swissborg);
            CryptoAsset swissBorgSwissborg = new CryptoAsset("SwissBorg", "CHSB", swissborg);
            CryptoAsset cronosCcom = new CryptoAsset("Cronos", "CRO", cCom);
            CryptoAsset cardanoCcom = new CryptoAsset("Cardano", "ADA", cCom);
            CryptoAsset usdCoinCelcius = new CryptoAsset("USD Coin", "USDC", celcius);

            // Creates the current value crypto asset objects 
            BigDecimal btcValue = new BigDecimal("19583.39");
            BigDecimal daiValue = new BigDecimal("1");
            BigDecimal ethValue = new BigDecimal("1660.99");
            BigDecimal chilizValue = new BigDecimal("57.01");
            BigDecimal uniswapValue = new BigDecimal("10.57");
            BigDecimal swissborgValue = new BigDecimal("4.20");
            BigDecimal cronosValue = new BigDecimal("0.1197");
            BigDecimal cardanoValue = new BigDecimal("27.03");
            BigDecimal usdCoinValue = new BigDecimal("27.03");

            // Adds the current value to the crypto asset objects
            bitcoinBitpanda.setCurrentValueFiat(btcValue);
            bitcoinNexo.setCurrentValueFiat(btcValue);
            daiDeFi.setCurrentValueFiat(daiValue);
            ethDeFi.setCurrentValueFiat(ethValue);
            chilizSwissborg.setCurrentValueFiat(chilizValue);
            uniswapSwissborg.setCurrentValueFiat(uniswapValue);
            swissBorgSwissborg.setCurrentValueFiat(swissborgValue);
            cronosCcom.setCurrentValueFiat(cronosValue);
            cardanoCcom.setCurrentValueFiat(cardanoValue);
            usdCoinCelcius.setCurrentValueFiat(usdCoinValue);

            // Saves the crypto assets objects to the repository
            cryptoAssetRepository.save(bitcoinBitpanda);
            cryptoAssetRepository.save(bitcoinNexo);
            cryptoAssetRepository.save(daiDeFi);
            cryptoAssetRepository.save(ethDeFi);
            cryptoAssetRepository.save(chilizSwissborg);
            cryptoAssetRepository.save(uniswapSwissborg);
            cryptoAssetRepository.save(cronosCcom);
            cryptoAssetRepository.save(cardanoCcom);
            cryptoAssetRepository.save(usdCoinCelcius);


            // Creates the fiat assets objects 
            FiatAsset chf = new FiatAsset("Schweizer Franken", "CHF", postFinance, true);

            // Saves the fiat assets objects to the repository
            fiatAssetRepository.save(chf);

            // Creates the transaction objects 
            Transaction T = new Transaction(LocalDateTime.now(), new BigDecimal("93.21"), chf, new BigDecimal("0.00217952"), bitcoinBitpanda, postFinance, bitpanda, new BigDecimal("1"), chf);
            Transaction T1 = new Transaction(LocalDateTime.now(), new BigDecimal("49.62"), chf, new BigDecimal("0.00240903"), bitcoinBitpanda, postFinance, bitpanda, new BigDecimal("2"), chf);
            Transaction T2 = new Transaction(LocalDateTime.now(), new BigDecimal("100"), chf, new BigDecimal("100"), daiDeFi, postFinance, deFiWallet, new BigDecimal("2"), chf);
            Transaction T3 = new Transaction(LocalDateTime.now(), new BigDecimal("1.25"), chf, new BigDecimal("0.000722487"), ethDeFi, postFinance, deFiWallet, new BigDecimal("2"), chf);
            Transaction T4 = new Transaction(LocalDateTime.now(), new BigDecimal("25.00"), chf, new BigDecimal("294.71"), chilizSwissborg, postFinance, swissborg, new BigDecimal("2"), chf);
            Transaction T5 = new Transaction(LocalDateTime.now(), new BigDecimal("8"), chf, new BigDecimal("1.68049"), uniswapSwissborg, postFinance, swissborg, new BigDecimal("2"), chf);
            Transaction T6 = new Transaction(LocalDateTime.now(), new BigDecimal("900"), chf, new BigDecimal("13987"), cronosCcom, postFinance, cCom, new BigDecimal("2"), chf);
            Transaction T7 = new Transaction(LocalDateTime.now(), new BigDecimal("70.00"), chf, new BigDecimal("53.3"), cardanoCcom, postFinance, cCom, new BigDecimal("2"), chf);
            Transaction T8 = new Transaction(LocalDateTime.now(), new BigDecimal("5631.71"), chf, new BigDecimal("5631.71"), usdCoinCelcius, postFinance, celcius, new BigDecimal("2"), chf);

            // Saves the transaction objects to the repository
            transactionRepository.save(T);
            transactionRepository.save(T1);
            transactionRepository.save(T2);
            transactionRepository.save(T3);
            transactionRepository.save(T4);
            transactionRepository.save(T5);
            transactionRepository.save(T6);
            transactionRepository.save(T7);
            transactionRepository.save(T8);


        };
    }

}
