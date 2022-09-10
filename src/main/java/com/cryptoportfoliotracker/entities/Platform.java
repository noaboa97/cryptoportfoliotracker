package com.cryptoportfoliotracker.entities;

import com.cryptoportfoliotracker.logic.CptService;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/***
 * Class for platform where the asset is kept
 *
 * @author Noah Li Wan Po
 * @version 1.0
 */
@Entity
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="platform_id")
    private Long id;

    @NotNull
    private String name;
    private boolean isFiatPlatform = false;

    /**
     * Creates a new platform instance
     *
     * @param name of the platform
     */
    public Platform(String name) {
        this.name = name;
    }


    /**
     * Creates a new platform instance
     *
     * @param name full name of the platform
     * @param isFiatPlatform true or false if it's a fiat platform or not
     */
    public Platform(String name, boolean isFiatPlatform) {

        this.name = name;
        this.isFiatPlatform = isFiatPlatform;
    }

    /**
     * Creates a new empty platform instance
     * Need for the bean creation of Spring because it uses the setter and getter methods
     *
     */
    public Platform() {
    }

    /**
     * Getter for the id of the platform
     *
     * @return  Long
     *          The id of the platform
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for the id of the platform
     *
     * @param id
     *        The id of platform
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the name of the platform
     *
     * @return  String
     *          The name of the platform
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the platform
     *
     * @param name
     *        The name of platform
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to calculate the invested fiat capital of the platform
     *
     * @param service Controller which lets the view talk to the model
     * @return The invested fiat capital of the platform
     * @see CptService
     */
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

    /***
     * Method to calculate the current value of the crypto asset in fiat
     *
     * @param service Controller which lets the view talk to the model
     * @return The current fiat value of the crypto asset
     * @see CptService
     */
    public BigDecimal getCurrentValueFiat(CptService service) {

        BigDecimal currentValueFiat = new BigDecimal("0");

        List<CryptoAsset> assetList = service.findAllCryptoAssetsOfPlatform(this);

        for (CryptoAsset a : assetList){

            List<Transaction> transactionList = service.findBySrcPlatform(service.findStandard().getPlatform());

            for(Transaction t : transactionList) {
                if(t.getDestPlatform().toString() == this.toString()) {

                    BigDecimal la = a.getCurrentValueFiat();
                    BigDecimal lo = t.getDestAmount().multiply(la);
                    currentValueFiat = currentValueFiat.add(lo);

                }
            }
        }

        return currentValueFiat.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Getter to check if it's a fiat platform
     *
     * @return  boolean
     *          true or false
     */
    public boolean isFiatPlatform() {
        return isFiatPlatform;
    }

    /**
     * Setter if it's a fiat platform otherwise by default it's false
     *
     * @param fiatPlatform
     *        true or false
     */
    public void setFiatPlatform(boolean fiatPlatform) {
        isFiatPlatform = fiatPlatform;
    }

    /***
     * Override of the said method that the name is retrieved instead of the object reference
     *
     * @return name of the platform
     */
    @Override
    public String toString(){
        return name;
    }

}
