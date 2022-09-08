package com.cryptoportfoliotracker.logic;

import com.cryptoportfoliotracker.entities.*;
import com.cryptoportfoliotracker.repository.AssetRepository;
import com.cryptoportfoliotracker.repository.CryptoAssetRepository;
import com.cryptoportfoliotracker.repository.PlatformRepository;
import com.cryptoportfoliotracker.repository.TransactionRepository;
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


    public CptService(AssetRepository assetRepository,
                      CryptoAssetRepository cryptoAssetRepository,
                      PlatformRepository platformRepository,
                      TransactionRepository transactionRepository) {
        this.assetRepository = assetRepository;
        this.cryptoAssetRepository = cryptoAssetRepository;
        this.platformRepository = platformRepository;
        this.transactionRepository = transactionRepository;
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
        if (cryptoAsset == null) {


            System.err.println("Transaction is null. Are you sure you have connected your form to the application?");
            return;
        }
        System.out.println("CptService saveTransaction");

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
        if (transaction == null) {


            System.err.println("Transaction is null. Are you sure you have connected your form to the application?");
            return;
        }
        System.out.println("CptService saveTransaction");
        transaction.getTransaction();
        transactionRepository.save(transaction);
    }

    public BigDecimal getCurrentValue() {
        BigDecimal currentValue = new BigDecimal("0");
        String s = new String("Test");
        for (Transaction t : findAllTransactions("")) {

            // get dest asset fullname and search for it in the repo get the first object of the list - assuming there is one - none will throw error
            CryptoAsset ca = findAllCryptoAssets(t.getDestAsset().getFullname()).get(0);

            // multiply the destination amount with the current value of the crypto asset
            currentValue = currentValue.add(t.getDestAmount().multiply(ca.getCurrentValueFiat()));

        }
        return currentValue;
    }

    public BigDecimal PercentageChange() {
        BigDecimal increase, pIncreace, decrease, pDecrease;
        BigDecimal h = new BigDecimal("100");
        //https://www.educative.io/answers/how-to-compare-two-bigdecimals-in-java
        //https://www.investopedia.com/terms/p/percentage-change.asp
        if (getInvestedCapital().compareTo(getCurrentValue()) < 0) {
            // increase calc
            increase = getCurrentValue().subtract(getInvestedCapital());
            pIncreace = increase.divide(getInvestedCapital(), 5, RoundingMode.HALF_EVEN).multiply(h);
            return pIncreace;
        } else {
            // decrease calc
            decrease = getInvestedCapital().subtract(getCurrentValue());
            pDecrease = decrease.divide(getInvestedCapital(), 5, RoundingMode.HALF_EVEN).multiply(h);
            return pDecrease;

        }


    }
    public BigDecimal getInvestedCapital() {
        BigDecimal investedCapital = new BigDecimal("0");

        for (Transaction t : findAllTransactions("")) {
            investedCapital = investedCapital.add(t.getSrcAmount());
        }

        return investedCapital;

    }
}
