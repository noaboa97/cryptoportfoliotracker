package com.cryptoportfoliotracker.entities;

import com.cryptoportfoliotracker.logic.CptService;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Superclass for asset types
 *
 * @author Noah Li Wan Po
 * @version 1.0
 * @see CryptoAsset
 * @see FiatAsset
 */
@Entity
public abstract class Asset {

    /** Represents the asset id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "asset_id")
    private Long id;

    /** Represents the full name of the asset
     */
    @NotNull
    private String fullName;

    /** Represents the short name of the asset
     */
    @NotNull
    private String shortName;

    /** Represents the platform of the asset
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "platform", foreignKey = @javax.persistence.ForeignKey(name = "platform_fk"))
    private Platform platform;

    /**
     * Creates a new Asset instance
     *
     * @param fullName  the name of the asset
     * @param shortName the name of the asset
     * @param platform  where the asset is held
     */
    public Asset(String fullName, String shortName, Platform platform) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.platform = platform;
    }

    /**
     * Creates a new empty asset instance
     * Need for the bean creation of Spring because it uses the setter and getter methods
     */
    public Asset() {
    }

    /**
     * Getter for the id
     *
     * @return the id of the asset
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for the id
     *
     * @param id The identifier of the asset
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the full name
     *
     * @return The full name of the asset
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Setter for the full name of the asset
     *
     * @param fullName The name of the asset
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Getter for the short name
     *
     * @return The short name of the asset
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Setter for the short name of the asset
     *
     * @param shortName The name of the asset
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * Getter for the platform
     *
     * @return Platform
     * The platform of the asset
     * @see Platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * Setter for the short name of the asset
     *
     * @param platform The platform name of the asset
     */
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    /**
     * Defines abstract method to be implemented by the subclasses
     *
     * @param service Controller which lets the view talk to the model
     * @return The invested fiat capital in the asset
     * @see CptService
     */
    public abstract BigDecimal getInvestedCapitalFiat(CptService service);

    /**
     * Defines abstract method to be implemented by the subclasses
     *
     * @param service Controller which lets the view talk to the model
     * @return The invested crypto capital in the asset
     * @see CptService
     */
    public abstract BigDecimal getInvestedCapitalCrypto(CptService service);

    /***
     * Defines abstract method to be implemented by the subclasses
     *
     * @param service Controller which lets the view talk to the model
     * @return The current fiat value of the asset
     * @see CptService
     */
    public abstract BigDecimal getCurrentValueFiat(CptService service);

    /***
     * Override of the said method that the short name is retrieved instead of the object reference
     *
     * @return short name of the asset
     */
    @Override
    public String toString() {
        return shortName;
    }

    public String getShortNameAndPlatform() {

        String shortNameAndPlatform = getShortName() + " - " + getPlatform().getName();

        return shortNameAndPlatform;
    }

}
