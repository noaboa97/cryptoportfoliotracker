package com.cryptoportfoliotracker.entities;

import com.cryptoportfoliotracker.logic.CptService;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "asset_id")
    private Long id;
    @NotNull
    private String fullName;
    @NotNull
    private String shortName;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "platform", foreignKey = @javax.persistence.ForeignKey(name = "platform_fk"))
    private Platform platform;

    /**
     * Creates a new Asset instance
     *
     * @param fullName the name of the asset
     * @param shortName the name of the asset
     * @param platform where the asset is held
     */
    public Asset(String fullName, String shortName, Platform platform) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.platform = platform;
    }

    /**
     * Creates a new empty asset instance
     * Need for the bean creation of Spring because it uses the setter and getter methods
     *
     */
    public Asset() {
    }

    /**
     * Getter for the id
     *
     * @Return the id of the asset
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for the id
     *
     * @param id
     *        The identifier of the asset
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the full name
     *
     * @Return The full name of the asset
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Setter for the full name of the asset
     *
     * @param fullName
     *        The name of the asset
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Getter for the short name
     *
     * @Return The short name of the asset
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Setter for the short name of the asset
     *
     * @param shortName
     *        The name of the asset
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * Getter for the platform
     *
     * @Return  Platform
     *          The platform of the asset
     * @see Platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * Setter for the short name of the asset
     *
     * @param platform
     *        The platform name of the asset
     */
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    /**
     * Defines abstract method to be implemented by the subclasses
     *
     * @param service Controller which lets the view talk to the model
     * @Return The invested fiat capital in the asset
     * @see CptService
     */
    public abstract BigDecimal getInvestedCapitalFiat(CptService service);

    /**
     * Defines abstract method to be implemented by the subclasses
     *
     * @param service Controller which lets the view talk to the model
     * @Return The invested crypto capital in the asset
     * @see CptService
     */
    public abstract BigDecimal getInvestedCapitalCrypto(CptService service);

    /***
     * Defines abstract method to be implemented by the subclasses
     *
     * @param service Controller which lets the view talk to the model
     * @Return The current fiat value of the asset
     * @see CptService
     */
    public abstract BigDecimal getCurrentValueFiat(CptService service);

    /***
     * Override of the said method that the short name is retrieved instead of the object reference
     *
     * @Return short name of the asset
     */
    @Override
    public String toString() {
        return shortName;
    }
}
