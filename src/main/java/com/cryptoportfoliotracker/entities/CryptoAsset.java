package com.cryptoportfoliotracker.entities;

import com.cryptoportfoliotracker.entities.Asset;
import com.cryptoportfoliotracker.entities.Platform;

import java.math.BigDecimal;

public class CryptoAsset extends Asset {

    private BigDecimal investedCapitalFiat; // stores the invested money in fiat
    private BigDecimal investedCapitalCrypto; // stores the invested amount in crypto
    private BigDecimal currentBalanceCrypto; // stores the total value in crypto
    private BigDecimal currentBalanceFiat; // stores the total value in fiat
    private BigDecimal interestEarnedFiat;
    private BigDecimal interestEarnedCrypto;

    public CryptoAsset(int id, String fullname, String shortname, Platform platform) {
        super(id, fullname, shortname, platform);
    }


    public BigDecimal getInvestedCapitalFiat() {
        return investedCapitalFiat;
    }

    public void setInvestedCapitalFiat(BigDecimal investedCapitalFiat) {
        this.investedCapitalFiat = investedCapitalFiat;
    }

    public BigDecimal getInvestedCapitalCrypto() {
        return investedCapitalCrypto;
    }

    public void setInvestedCapitalCrypto(BigDecimal investedCapitalCrypto) {
        this.investedCapitalCrypto = investedCapitalCrypto;
    }

    public BigDecimal getCurrentBalanceCrypto() {
        return currentBalanceCrypto;
    }

    public void setCurrentBalanceCrypto(BigDecimal currentBalanceCrypto) {
        this.currentBalanceCrypto = currentBalanceCrypto;
    }

    public BigDecimal getCurrentBalanceFiat() {
        return currentBalanceFiat;
    }

    public void setCurrentBalanceFiat(BigDecimal currentBalanceFiat) {
        this.currentBalanceFiat = currentBalanceFiat;
    }

    public BigDecimal getInterestEarnedFiat() {
        return interestEarnedFiat;
    }

    public void setInterestEarnedFiat(BigDecimal interestEarnedFiat) {
        this.interestEarnedFiat = interestEarnedFiat;
    }

    public BigDecimal getInterestEarnedCrypto() {
        return interestEarnedCrypto;
    }

    public void setInterestEarnedCrypto(BigDecimal interestEarnedCrypto) {
        this.interestEarnedCrypto = interestEarnedCrypto;
    }
}
