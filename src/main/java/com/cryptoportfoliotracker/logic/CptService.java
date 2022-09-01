package com.cryptoportfoliotracker.logic;

import com.cryptoportfoliotracker.entities.Asset;
import com.cryptoportfoliotracker.entities.CryptoAsset;
import com.cryptoportfoliotracker.entities.Platform;
import com.cryptoportfoliotracker.entities.Transaction;
import com.cryptoportfoliotracker.repository.AssetRepository;
import com.cryptoportfoliotracker.repository.CryptoAssetRepository;
import com.cryptoportfoliotracker.repository.PlatformRepository;
import com.cryptoportfoliotracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<CryptoAsset> findAllCryptoAssets() {
        return cryptoAssetRepository.findAll();

    }

    public List<Platform> findAllPlatforms() {
        return platformRepository.findAll();
    }

    public List<Transaction> findAllTransactions(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {


            return transactionRepository.findAll();
        } else {
            return transactionRepository.search(stringFilter);
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

}
