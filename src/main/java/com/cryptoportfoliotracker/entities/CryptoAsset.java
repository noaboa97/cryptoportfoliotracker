package com.cryptoportfoliotracker.entities;

import com.cryptoportfoliotracker.logic.CptService;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Entity
public class CryptoAsset extends Asset {
    private BigDecimal currentValueFiat;

    public CryptoAsset(String fullname, String shortname, Platform platform) {
        super(fullname, shortname, platform);
    }

    public CryptoAsset() {
        super();
    }

    // calculates how much money has been invested into crypto by adding all the source (CHF) amount


    public BigDecimal getInvestedCapitalCrypto(CptService service) {

        BigDecimal investedCapitalCrypto = new BigDecimal("0");

        List<Transaction> list = service.findByDestAsset(this);

        for (Transaction t : list) {
            if (t.getDestPlatform().getName() == this.getPlatform().getName()) {
                investedCapitalCrypto = investedCapitalCrypto.add(t.getDestAmount());
            }

        }

        return investedCapitalCrypto;
    }

    public BigDecimal getInvestedCapitalFiat(CptService service) {

        BigDecimal investedCapitalFiat = new BigDecimal("0");
        List<Transaction> list = service.findBySrcAsset((Asset)service.findStandard());
        for (Transaction t : list) {
            if (t.getDestPlatform().getName() == this.getPlatform().getName()) {
                investedCapitalFiat = investedCapitalFiat.add(t.getSrcAmount());
            }
        }
        return investedCapitalFiat;
    }

    public BigDecimal getCurrentValueFiat(CptService service) {

        BigDecimal currentValueFiat = new BigDecimal("0");

        List<Transaction> list = service.findByDestAsset(this);

        for (Transaction t : list) {

            if (t.getDestPlatform().getName() == this.getPlatform().getName()) {
                BigDecimal DestAmountCurrentValue = t.getDestAmount().multiply(this.currentValueFiat);
                currentValueFiat = currentValueFiat.add(DestAmountCurrentValue);
            }
        }

        return currentValueFiat.setScale(2, RoundingMode.HALF_UP);
    }


    public BigDecimal getCurrentValueFiat() {
        return currentValueFiat;
    }

    public void setCurrentValueFiat(BigDecimal currentValueFiat) {
        this.currentValueFiat = currentValueFiat;
    }
}
