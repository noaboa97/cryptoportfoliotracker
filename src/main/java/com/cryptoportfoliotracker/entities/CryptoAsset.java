package com.cryptoportfoliotracker.entities;

import com.cryptoportfoliotracker.logic.CptService;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


/***
 * Class for crypto asset type e.g. BTC
 *
 * @author Noah Li Wan Po
 * @version 1.0
 * @see Asset
 * @see FiatAsset
 */
@Entity
public class CryptoAsset extends Asset {
    private BigDecimal currentValueFiat;

    /**
     * Creates a new crypto asset instance
     *
     * @param fullname full name of the asset
     * @param shortname short name of the asset
     * @param platform where the asset is held
     */
    public CryptoAsset(String fullname, String shortname, Platform platform) {
        super(fullname, shortname, platform);
    }

    /**
     * Creates a new empty crypto asset instance
     * Need for the bean creation of Spring because it uses the setter and getter methods
     *
     */
    public CryptoAsset() {
        super();
    }

    // calculates how much money has been invested into crypto by adding all the source (CHF) amount

    /**
     * Implements method to calculate the invested crypto capital of the crypto asset
     *
     * @param service Controller which lets the view talk to the model
     * @Return The invested crypto capital of the crypto asset
     * @see CptService
     */
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

    /**
     * Implements method to calculate the invested fiat capital of the crypto asset
     *
     * @param service Controller which lets the view talk to the model
     * @Return The invested fiat capital of the crypto asset
     * @see CptService
     */
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

    /***
     * Implements method to calculate the current value of the crypto asset in fiat
     *
     * @param service Controller which lets the view talk to the model
     * @Return The current fiat value of the crypto asset
     * @see CptService
     */
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

    /**
     * Getter for the current value in fiat of the crypto asset
     *
     * @Return  BigDecimal
     *          The current value in fiat of the crypto asset
     */
    public BigDecimal getCurrentValueFiat() {
        return currentValueFiat;
    }

    /**
     * Setter for the current value in fiat of the crypto asset
     *
     * @param currentValueFiat
     *        The current value in fiat of the crypto asset
     */
    public void setCurrentValueFiat(BigDecimal currentValueFiat) {
        this.currentValueFiat = currentValueFiat;
    }
}
