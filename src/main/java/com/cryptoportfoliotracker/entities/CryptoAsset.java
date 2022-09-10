package com.cryptoportfoliotracker.entities;

import com.cryptoportfoliotracker.logic.CptService;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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

    /**
     * Represents the current market price of the crypto asset
     */
    private BigDecimal currentValueFiat;

    @Transient
    DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Creates a new crypto asset instance
     *
     * @param fullname  full name of the asset
     * @param shortname short name of the asset
     * @param platform  where the asset is held
     */
    public CryptoAsset(String fullname, String shortname, Platform platform) {
        super(fullname, shortname, platform);
    }

    /**
     * Creates a new empty crypto asset instance
     * Need for the bean creation of Spring because it uses the setter and getter methods
     */
    public CryptoAsset() {
        super();
    }

    // calculates how much money has been invested into crypto by adding all the source (CHF) amount

    /**
     * Implements method to calculate the invested crypto capital of the crypto asset
     *
     * @param service Controller which lets the view talk to the model
     * @return The invested crypto capital of the crypto asset
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
     * Converts invested capital crypto to string
     *
     * @param service Controller which lets the view talk to the model
     * @return The invested crypto capital of the crypto asset as a string
     * @see CptService
     */
    public String getInvestedCapitalCryptoToString(CptService service) {
        return df.format(getInvestedCapitalCrypto(service));
    }

    /**
     * Implements method to calculate the invested fiat capital of the crypto asset
     *
     * @param service Controller which lets the view talk to the model
     * @return The invested fiat capital of the crypto asset
     * @see CptService
     */
    public BigDecimal getInvestedCapitalFiat(CptService service) {

        BigDecimal investedCapitalFiat = new BigDecimal("0");
        List<Transaction> list = service.findBySrcAsset(service.findStandard());
        for (Transaction t : list) {
            if (t.getDestPlatform().getName() == this.getPlatform().getName() && t.getDestAsset().getFullName() == this.getFullName()) {
                investedCapitalFiat = investedCapitalFiat.add(t.getSrcAmount());
            }
        }
        return investedCapitalFiat;
    }

    /**
     * Converts invested fiat capital to string
     *
     * @param service Controller which lets the view talk to the model
     * @return The invested crypto capital of the crypto asset as a string
     * @see CptService
     */
    public String getInvestedCapitalFiatToString(CptService service) {
        return df.format(getInvestedCapitalFiat(service));
    }

    /***
     * Implements method to calculate the current value of the crypto asset in fiat
     *
     * @param service Controller which lets the view talk to the model
     * @return The current fiat value of the crypto asset
     * @see CptService
     */
    public BigDecimal getCurrentValueFiat(CptService service) {

        BigDecimal currentValueFiat = new BigDecimal("0");

        List<Transaction> list = service.findByDestAsset(this);

        for (Transaction t : list) {

            if (t.getDestPlatform().getName() == this.getPlatform().getName() && t.getDestAsset().getFullName() == this.getFullName()) {
                BigDecimal DestAmountCurrentValue = t.getDestAmount().multiply(this.currentValueFiat);
                currentValueFiat = currentValueFiat.add(DestAmountCurrentValue);
            }
        }

        return currentValueFiat.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Converts current value of the crypto asset in fiat to string
     *
     * @param service Controller which lets the view talk to the model
     * @return current value of the crypto asset in fiat as a string
     * @see CptService
     */
    public String getCurrentValueFiatToString(CptService service) {
        return df.format(getCurrentValueFiat(service));
    }

    /**
     * Getter for the current value in fiat of the crypto asset
     *
     * @return The current value in fiat of the crypto asset as ({@code BigDecimal})
     */
    public BigDecimal getCurrentValueFiat() {
        return currentValueFiat;
    }

    /**
     * Setter for the current value in fiat of the crypto asset
     *
     * @param currentValueFiat The current value in fiat of the crypto asset
     */
    public void setCurrentValueFiat(BigDecimal currentValueFiat) {
        this.currentValueFiat = currentValueFiat;
    }
}
