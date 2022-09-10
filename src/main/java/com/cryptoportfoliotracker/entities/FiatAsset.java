package com.cryptoportfoliotracker.entities;

import com.cryptoportfoliotracker.logic.CptService;

import javax.persistence.Entity;
import java.math.BigDecimal;

/***
 * Class for fiat asset type e.g. CHF
 *
 * @author Noah Li Wan Po
 * @version 1.0
 * @see Asset
 * @see CryptoAsset
 */
@Entity
public class FiatAsset extends Asset{
    private boolean standard = false;

    /**
     * Creates a new fiat asset instance
     *
     * @param fullName full name of the asset
     * @param shortName short name of the asset
     * @param platform where the asset is held
     * @param standard if this is the default fiat asset to be used if there are multiple
     */
    public FiatAsset(String fullName, String shortName, Platform platform, boolean standard) {
        super(fullName, shortName, platform);
        this.standard = standard;
    }

    /**
     * Creates a new empty fiat asset instance
     * Need for the bean creation of Spring because it uses the setter and getter methods
     *
     */
    public FiatAsset() {
        super();
    }

    /**
     * Getter for the standard
     *
     * @return  boolean
     *          true or false
     */
    public boolean getStandard() {
        return standard;
    }

    /**
     * Setter for the current value in fiat of the crypto asset
     *
     * @param standard
     *        true or false
     */
    public void setStandard(boolean standard) {
        this.standard = standard;
    }

    /**
     * Not implemented yet currently out of scope for project
     * Should get what asset and how much has been invested from it in crypto
     *
     * @param service Controller which lets the view talk to the model
     * @return null
     * @see CptService
     */
    public BigDecimal getInvestedCapitalCrypto(CptService service){
        return null;
    }

    /***
     * Not implemented yet currently out of scope for project
     * Should get the fiat amount invested from this fiat asset
     *
     * @param service Controller which lets the view talk to the model
     * @return null
     * @see CptService
     */
    public BigDecimal getInvestedCapitalFiat(CptService service){
        return null;
    }

    /***
     * Not implemented yet currently out of scope for project
     * Should calculate the current value of the fiat asset into the standard fiat asset currency
     *
     * @param service Controller which lets the view talk to the model
     * @return null
     * @see CptService
     */
    public BigDecimal getCurrentValueFiat(CptService service){return null;}


}
