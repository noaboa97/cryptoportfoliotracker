package com.cryptoportfoliotracker.logic;

import com.cryptoportfoliotracker.entities.*;
import com.cryptoportfoliotracker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CptService {
    @Autowired
    private final AssetRepository assetRepository;
    @Autowired
    private final CryptoAssetRepository cryptoAssetRepository;
    @Autowired
    private final PlatformRepository platformRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    private FiatAssetRepository fiatAssetRepository;


    public CptService(AssetRepository assetRepository,
                      CryptoAssetRepository cryptoAssetRepository,
                      PlatformRepository platformRepository,
                      TransactionRepository transactionRepository,
                      FiatAssetRepository fiatAssetRepository) {
        this.assetRepository = assetRepository;
        this.cryptoAssetRepository = cryptoAssetRepository;
        this.platformRepository = platformRepository;
        this.transactionRepository = transactionRepository;
        this.fiatAssetRepository = fiatAssetRepository;
    }

    public List<Asset> findAllAssets() {
        return assetRepository.findAll();
    }

    public List<CryptoAsset> findAllCryptoAssets(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {


            return cryptoAssetRepository.findAll();
        } else {
            return cryptoAssetRepository.search(stringFilter);
        }
    }

    public long countCryptoAsset() {
        return cryptoAssetRepository.count();
    }

    public void deleteCryptoAsset(CryptoAsset cryptoAsset) {
        cryptoAssetRepository.delete(cryptoAsset);
    }

    public void saveCryptoAsset(CryptoAsset cryptoAsset) {
        cryptoAssetRepository.save(cryptoAsset);
    }


    public List<Platform> findAllPlatforms() {

        return platformRepository.findAll();
    }

    public List<Platform> findAllCryptoPlatforms() {

        boolean isFiatPlatform = false;

        return platformRepository.findAllCryptoPlatforms(isFiatPlatform);
    }


    public List<CryptoAsset> findAllCryptoAssetsOfPlatform(Platform p) {

        return cryptoAssetRepository.findAllCryptoAssetsOfPlatform(p);
    }

    public List<Transaction> findBySrcPlatform(Platform p) {

        return transactionRepository.findBySrcPlatform(p);
    }

    public List<Transaction> findByDestPlatform(Platform p) {

        return transactionRepository.findByDestPlatform(p);
    }


    public List<Transaction> findBySrcAsset(Asset a) {
        return transactionRepository.findBySrcAsset(a);
    }

    public FiatAsset findStandard() {
        return fiatAssetRepository.findStandard(true);
    }

    ;

    public List<Transaction> findByDestAsset(CryptoAsset ca) {

        return transactionRepository.findByDestAsset(ca);
    }

    public List<Transaction> findAllTransactions(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {


            return transactionRepository.findAll();
        } else {
            return null; //transactionRepository.search(stringFilter);
        }
    }

    public long countTransactions() {
        return transactionRepository.count();
    }

    public void deleteTransaction(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public BigDecimal getTotalCurrentValue() {
        BigDecimal currentValue = new BigDecimal("0");
        String s = new String("Test");
        for (Transaction t : findAllTransactions("")) {

            // get dest asset fullname and search for it in the repo get the first object of the list - assuming there is one - none will throw error
            CryptoAsset ca = findAllCryptoAssets(t.getDestAsset().getFullname()).get(0);

            // multiply the destination amount with the current value of the crypto asset
            currentValue = currentValue.add(t.getDestAmount().multiply(ca.getCurrentValueFiat()));

        }
        return currentValue.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotalPercentageChange() {
        BigDecimal increase, pIncreace, decrease, pDecrease;
        BigDecimal h = new BigDecimal("100");
        //https://www.educative.io/answers/how-to-compare-two-bigdecimals-in-java
        //https://www.investopedia.com/terms/p/percentage-change.asp
        if (getTotalInvestedCapital().compareTo(getTotalCurrentValue()) < 0) {
            // increase calc
            increase = getTotalCurrentValue().subtract(getTotalInvestedCapital());
            pIncreace = increase.divide(getTotalInvestedCapital(), 5, RoundingMode.HALF_EVEN).multiply(h);
            return pIncreace.setScale(2, RoundingMode.HALF_UP);
        } else {
            // decrease calc
            decrease = getTotalInvestedCapital().subtract(getTotalCurrentValue());
            pDecrease = decrease.divide(getTotalInvestedCapital(), 5, RoundingMode.HALF_EVEN).multiply(h);
            return pDecrease.negate().setScale(2, RoundingMode.HALF_UP);

        }


    }


    // TO DO!!!----------------------------------------------------
    public double getPlatformPercentageChange(Platform platform) {
        BigDecimal increase, pIncreace, decrease, pDecrease;
        BigDecimal h = new BigDecimal("100");

        //https://www.educative.io/answers/how-to-compare-two-bigdecimals-in-java
        //https://www.investopedia.com/terms/p/percentage-change.asp
        BigDecimal investedCapital = platform.getInvestedCapitalFiat(this);
        BigDecimal currentValue = platform.getCurrentValueFiat(this);

        if (investedCapital.compareTo(currentValue) == 0) {
            return 0.00;
        } else if (investedCapital.compareTo(currentValue) < 0) {
            // increase calc
            increase = currentValue.subtract(investedCapital);
            pIncreace = increase.divide(investedCapital, 5, RoundingMode.HALF_EVEN).multiply(h);
            return pIncreace.setScale(2, RoundingMode.HALF_UP).doubleValue();
        } else {
            // decrease calc
            decrease = investedCapital.subtract(currentValue);
            pDecrease = decrease.divide(investedCapital, 5, RoundingMode.HALF_EVEN).multiply(h);
            return pDecrease.negate().setScale(2, RoundingMode.HALF_UP).doubleValue();

        }


    }


    public BigDecimal getTotalInvestedCapital() {
        BigDecimal investedCapital = new BigDecimal("0");

        for (Transaction t : findAllTransactions("")) {
            investedCapital = investedCapital.add(t.getSrcAmount());
        }

        return investedCapital.setScale(2, RoundingMode.HALF_UP);

    }
}
