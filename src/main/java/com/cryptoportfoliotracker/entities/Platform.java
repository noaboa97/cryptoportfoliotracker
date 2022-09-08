package com.cryptoportfoliotracker.entities;

import com.cryptoportfoliotracker.logic.CptService;
import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private boolean isFiatPlatform = false;
    private BigDecimal currentValueFiat;

    public Platform(String name) {
        this.name = name;
    }

    public Platform(String name, boolean isFiatPlatform) {

        this.name = name;
        this.isFiatPlatform = isFiatPlatform;
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

        List<CryptoAsset> assetList = service.findAllCryptoAssetsOfPlatform(this);

        for (CryptoAsset a : assetList){

            List<Transaction> transactionList = service.findBySrcPlatform(service.findStandard().getPlatform());

            for(Transaction t : transactionList) {

                if (t.getDestPlatform().toString() == this.toString()) {

                    currentBalanceFiat = currentBalanceFiat.add(t.getSrcAmount());

                }


            }
        }

        return currentBalanceFiat.setScale(2, RoundingMode.HALF_UP);
    }
/*
    public BigDecimal getInvestedCapitalCrypto(CptService service) {

        BigDecimal currentBalanceCrypto = new BigDecimal("0");

        List<CryptoAsset> assetlist = service.findAllAssetsOfPlatform(this);

        for (CryptoAsset a : assetlist) {

            List<Transaction> transactionList = service.findByDestPlatform(this);

            for (Transaction t : transactionList){

                currentBalanceCrypto = currentBalanceCrypto.add(t.getDestAmount());
            }

        }

        return currentBalanceCrypto;
    }*/


    public BigDecimal getCurrentValueFiat(CptService service) {

        BigDecimal currentValueFiat = new BigDecimal("0");

        List<CryptoAsset> assetList = service.findAllCryptoAssetsOfPlatform(this);

        System.out.println("CV A List "+assetList.size());

        for (CryptoAsset a : assetList){

            List<Transaction> transactionList = service.findBySrcPlatform(service.findStandard().getPlatform());

            System.out.println("CV T List "+transactionList.size());

            for(Transaction t : transactionList) {
                t.getTransaction();
                if(t.getDestPlatform().toString() == this.toString()) {

                    BigDecimal la = a.getCurrentValueFiat();
                    BigDecimal lo = t.getDestAmount().multiply(la);
                    currentValueFiat = currentValueFiat.add(lo);

                }
            }
        }

        return currentValueFiat.setScale(2, RoundingMode.HALF_UP);
    }

    public boolean isFiatPlatform() {
        return isFiatPlatform;
    }

    public void setFiatPlatform(boolean fiatPlatform) {
        isFiatPlatform = fiatPlatform;
    }

    @Override
    public String toString(){
        return name;
    }

}
