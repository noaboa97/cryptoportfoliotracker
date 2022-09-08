package com.cryptoportfoliotracker.entities;

import com.cryptoportfoliotracker.logic.CptService;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class CryptoAsset extends Asset {
    private BigDecimal currentValueFiat;

    private BigDecimal investedCapitalCrypto; // stores the invested amount in crypto
    private BigDecimal currentBalanceCrypto; // stores the total value in crypto
    private BigDecimal currentBalanceFiat; // stores the total value in fiat
    private BigDecimal interestEarnedFiat;
    private BigDecimal interestEarnedCrypto;

    public CryptoAsset( String fullname, String shortname, Platform platform) {
        super( fullname, shortname, platform);
    }

    public CryptoAsset() {
        super();
    }

    // calculates how much money has been invested into crypto by adding all the source (CHF) amount


    public BigDecimal getInvestedCapitalCrypto(CptService service) {

        BigDecimal investedCapitalCrypto = new BigDecimal("0");

        List<Transaction> list = service.findByDestAsset(this);

        for ( Transaction t : list){

            investedCapitalCrypto.add(t.getDestAmount());

        }

        return investedCapitalCrypto;
    }

    public BigDecimal getInvestedCapitalFiat(CptService service) {

        BigDecimal investedCapitalFiat = new BigDecimal("0");

        List<Transaction> list = service.findBySrcAsset(this);
        for ( Transaction t : list){

            // wenn dest assset == this
            investedCapitalFiat.add(t.getSrcAmount());

        }
        investedCapitalFiat = investedCapitalFiat;
        return investedCapitalFiat;
    }

    /* TO DO
    public BigDecimal getCurrentBalanceCrypto(CptService service) {

        BigDecimal currentBalanceCrypto = new BigDecimal("0");

        List<Transaction> list = service.findByDestAsset(this.getFullname());

        for ( Transaction t : list){

            currentBalanceCrypto.add(t.getDestAmount().multiply(this.currentValue));

        }

        //

        return currentBalanceCrypto;
    }*/



    public BigDecimal getCurrentValueFiat(CptService service) {

        BigDecimal currentBalanceFiat = new BigDecimal("0");

        List<Transaction> list = service.findByDestAsset(this);

        for ( Transaction t : list){

            currentBalanceFiat.add(t.getDestAmount().multiply(this.currentValueFiat));

        }

        return currentBalanceFiat;
    }



    public BigDecimal getCurrentValueFiat() {
        return currentValueFiat;
    }

    public void setCurrentValueFiat(BigDecimal currentValueFiat) {
        this.currentValueFiat = currentValueFiat;
    }
}
