package com.cryptoportfoliotracker.logic;

import com.cryptoportfoliotracker.entities.*;
import com.cryptoportfoliotracker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Controller which lets the view talk to the model
 *
 * @author Noah Li Wan Po
 * @version 1.0
 */
@Service
public class CptService {
    @Autowired
    private final AssetRepository assetRepository;
    @Autowired
    private final CryptoAssetRepository cryptoAssetRepository;
    @Autowired
    private final PlatformRepository platformRepository;
    @Autowired
    private final TransactionRepository transactionRepository;
    @Autowired
    private final FiatAssetRepository fiatAssetRepository;


    /**
     * Creates a new transaction instance
     *
     * @param assetRepository The asset repository
     * @param cryptoAssetRepository The crypto asset repository
     * @param platformRepository The platform repository
     * @param transactionRepository The transaction repository
     * @param fiatAssetRepository The fiat asset repository
     */
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

    /**
     * Returns all assets in a list
     *
     * @Return list of all assets
     * @see AssetRepository
     */
    public List<Asset> findAllAssets() {
        return assetRepository.findAll();
    }

    /**
     * Returns all crypto assets or one crypto asset
     *
     * @Return all crypto assets or one crypto asset
     * @see CryptoAssetRepository
     */
    public List<CryptoAsset> findAllCryptoAssets(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {

            return cryptoAssetRepository.findAll();
        } else {
            return cryptoAssetRepository.search(stringFilter);
        }
    }

    /**
     * Deletes one crypto asset
     *
     * @param cryptoAsset crypto asset to be deleted
     * @see CryptoAssetRepository
     */
    public void deleteCryptoAsset(CryptoAsset cryptoAsset) {
        cryptoAssetRepository.delete(cryptoAsset);
    }

    /**
     * Saves one crypto asset
     *
     * @param cryptoAsset crypto asset to be saved
     * @see CryptoAssetRepository
     */
    public void saveCryptoAsset(CryptoAsset cryptoAsset) {
        cryptoAssetRepository.save(cryptoAsset);
    }

    /**
     * Returns all platforms in a list
     *
     * @Return list of all platforms
     * @see PlatformRepository
     */
    public List<Platform> findAllPlatforms() {

        return platformRepository.findAll();
    }

    /**
     * Returns all crypto platforms in a list
     *
     * @Return all crypto platforms in a list
     * @see PlatformRepository
     */
    public List<Platform> findAllCryptoPlatforms() {
        boolean isFiatPlatform = false;
        return platformRepository.findAllCryptoPlatforms(isFiatPlatform);
    }

    /**
     * Returns all crypto assets of a platforms in a list
     *
     * @Return all crypto assets of a platforms in a list
     * @see CryptoAssetRepository
     */
    public List<CryptoAsset> findAllCryptoAssetsOfPlatform(Platform p) {
        return cryptoAssetRepository.findAllCryptoAssetsOfPlatform(p);
    }

    public List<Asset> findAllAssetsOfPlatform(Platform p) {
        return assetRepository.findAllAssetsOfPlatform(p);
    }

    /**
     * Returns all transaction filtered by source platform in a list
     *
     * @Return Returns all transaction by source platform in a list
     * @see TransactionRepository
     */
    public List<Transaction> findBySrcPlatform(Platform p) {
        return transactionRepository.findBySrcPlatform(p);
    }

    /**
     * Returns all transaction filtered by destination platform in a list
     *
     * @Return Returns all transaction by destination platform in a list
     * @see TransactionRepository
     */
    public List<Transaction> findByDestPlatform(Platform p) {
        return transactionRepository.findByDestPlatform(p);
    }

    /**
     * Returns all transaction filtered by source asset in a list
     *
     * @Return Returns all transaction by source asset in a list
     * @see TransactionRepository
     */
    public List<Transaction> findBySrcAsset(Asset a) {
        return transactionRepository.findBySrcAsset(a);
    }

    /**
     * Returns the default fiat platform
     *
     * @Return Returns the default fiat platform
     * @see FiatAssetRepository
     */
    public FiatAsset findStandard() {
        return fiatAssetRepository.findStandard(true);
    }

    /**
     * Returns all transaction filtered by destination asset in a list
     *
     * @Return Returns all transaction by destination asset in a list
     * @see TransactionRepository
     */
    public List<Transaction> findByDestAsset(CryptoAsset ca) {

        return transactionRepository.findByDestAsset(ca);
    }

    /**
     * Returns all transactions or one transaction in a list
     *
     * @Return all transactions or one transaction in a list
     * @see TransactionRepository
     */
    public List<Transaction> findAllTransactions(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return transactionRepository.findAll();
        } else {
            return transactionRepository.search(stringFilter);
        }
    }

    /**
     * Deletes one transaction
     *
     * @param transaction to be deleted
     * @see TransactionRepository
     */
    public void deleteTransaction(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

    /**
     * Saves one transaction
     *
     * @param transaction to be saved
     * @see TransactionRepository
     */
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    /**
     * Calculates the total current value of all assets
     *
     * @Return BigDecimal
     *         Returns the total current value of all assets from the transactions
     */
    public BigDecimal getTotalCurrentValue() {
        BigDecimal currentValue = new BigDecimal("0");
        for (Transaction t : findAllTransactions("")) {

            // get dest asset fullname and search for it in the repo get the first object of the list - assuming there is one - none will throw error
            CryptoAsset ca = findAllCryptoAssets(t.getDestAsset().getFullName()).get(0);

            // multiply the destination amount with the current value of the crypto asset
            currentValue = currentValue.add(t.getDestAmount().multiply(ca.getCurrentValueFiat()));

        }
        return currentValue.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Calculates the total percentage change of invested to current value
     *
     * @Return BigDecimal
     *         Returns the total percentage change of invested to current value
     */
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

    /**
     * Calculates the platform percentage change of invested to current value
     *
     * @Return BigDecimal
     *         Returns the platform percentage change of invested to current value
     */
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

    /**
     * Calculates the total invested capital
     *
     * @Return BigDecimal
     *         Returns the total invested capital
     */
    public BigDecimal getTotalInvestedCapital() {
        BigDecimal investedCapital = new BigDecimal("0");

        for (Transaction t : findAllTransactions("")) {
            investedCapital = investedCapital.add(t.getSrcAmount());
        }

        return investedCapital.setScale(2, RoundingMode.HALF_UP);

    }
}
