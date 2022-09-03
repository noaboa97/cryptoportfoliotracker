package com.cryptoportfoliotracker.entities;

import com.cryptoportfoliotracker.logic.CptService;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="platform_id")
    private Long id;

    //@NotNull
    private String name;
    private BigDecimal investedBalanceFiat;
    private BigDecimal investedBalanceCrypto;
    //private BigDecimal currentBalanceFiat;
    private BigDecimal currentValueFiat;

    public Platform(String name) {
        this.name = name;
    }

    public Platform() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCurrentValueFiat() {
        return currentValueFiat;
    }

    public void setCurrentValueFiat(BigDecimal currentValueFiat) {
        this.currentValueFiat = currentValueFiat;
    }

    public BigDecimal getInvestedCapitalFiat(CptService service) {

        BigDecimal currentBalanceFiat = new BigDecimal("0");

        List<CryptoAsset> assetList = service.findAllAssetsOfPlatform(this);

        for (CryptoAsset a : assetList){

            List<Transaction> transactionList = service.findBySrcPlatform(this);

            for(Transaction t : transactionList)

                currentBalanceFiat = currentBalanceFiat.add(t.getSrcAmount());

        }

        return currentBalanceFiat;
    }

    public BigDecimal getInvestedCapitalCrypto(CptService service) {

        BigDecimal currentBalanceCrypto = new BigDecimal("0");

        List<CryptoAsset> assetlist = service.findAllAssetsOfPlatform(this);

        for (CryptoAsset a : assetlist){

            List<Transaction> transactionList = service.findByDestPlatform(this);

            for(Transaction t : transactionList)

                currentBalanceCrypto = currentBalanceCrypto.add(t.getDestAmount());

        }

        return currentBalanceCrypto;
    }


    public BigDecimal getCurrentBalanceFiat(CptService service) {

        BigDecimal currentBalanceFiat = new BigDecimal("0");

        List<CryptoAsset> assetlist = service.findAllAssetsOfPlatform(this);

        for (CryptoAsset a : assetlist){

            List<Transaction> transactionList = service.findBySrcPlatform(this);

            for(Transaction t : transactionList)

                currentBalanceFiat = currentBalanceFiat.add(t.getSrcAmount().multiply(a.getCurrentValueFiat()));

        }

        return currentBalanceFiat;
    }

/*
    public BigDecimal getInvestedBalance() {
        return investedBalance;
    }

    public void setInvestedBalance(BigDecimal investedBalance) {
        this.investedBalance = investedBalance;
    }

    public BigDecimal getCurrentBalance() {
        return CurrentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        CurrentBalance = currentBalance;
    }*/
}
