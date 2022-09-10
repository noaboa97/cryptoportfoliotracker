package com.cryptoportfoliotracker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/***
 * Class for a transaction which holds information about what has been exchanged or transferred
 *
 * @author Noah Li Wan Po
 * @version 1.0
 * @see Asset
 * @see Platform
 */
@Entity
public class Transaction {

    /** Represents the id of the transactions
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private Long id;

    /** Represents the date and time of the transaction
     */
    @NotNull
    private LocalDateTime dateAndTime;

    /** Represents the source amount of the transaction
     */
    @NotNull
    @Column(precision = 30, scale = 10)
    private BigDecimal srcAmount;

    /** Represents the source asset of the transaction
     */
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "srcasset",
            foreignKey = @javax.persistence.ForeignKey(name = "srcasset_fk")/*,insertable = false,updatable = false*/)
    private Asset srcAsset;

    /** Represents the destination amount of the transaction
     */
    @NotNull
    @Column(precision = 30, scale = 10)
    private BigDecimal destAmount;

    /** Represents the destination asset of the transaction
     */
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "destasset",
            foreignKey = @javax.persistence.ForeignKey(name = "destasset_fk")/*,insertable = false,updatable = false*/)
    private Asset destAsset;

    /** Represents the source platform of the transaction
     */
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "srcplatform",
            foreignKey = @javax.persistence.ForeignKey(name = "srcplatform_fk")/*,insertable = false,updatable = false*/)
    private Platform srcPlatform;

    /** Represents the destination platfrom of the transaction
     */
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "destplatform",
            foreignKey = @javax.persistence.ForeignKey(name = "destplatform_fk")/*,insertable = false,updatable = false*/)
    private Platform destPlatform;

    @Column(precision = 30, scale = 10)
    /** Represents the fee for the transaction
     */
    private BigDecimal fee;

    /** Represents the fee asset of the tranaction
     */
    @ManyToOne()
    @JoinColumn(name = "feeasset",
            foreignKey = @javax.persistence.ForeignKey(name = "feeasset_fk")/*,insertable = false,updatable = false*/)
    private Asset feeAsset;

    /** Represents the notes of the transaction
     */
    private String notes;

    @Transient
    DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Creates a new transaction instance
     *
     * @param dateAndTime  date and time of the transaction
     * @param srcAmount    source amount which has been transferred
     * @param srcAsset     source asset which has been transferred
     * @param destAmount   destination amount which has been transferred
     * @param destAsset    source asset which has been transferred
     * @param srcPlatform  source platform from which the transaction originated
     * @param destPlatform destination platform from where the transaction was sent to
     * @param fee          amount for the transaction
     * @param fee          asset of the fee amount
     */
    public Transaction(LocalDateTime dateAndTime, BigDecimal srcAmount, Asset srcAsset, BigDecimal destAmount, Asset destAsset, Platform srcPlatform, Platform destPlatform, BigDecimal fee, Asset feeAsset) {
        this.dateAndTime = dateAndTime;
        this.srcAmount = srcAmount;
        this.srcAsset = srcAsset;
        this.destAmount = destAmount;
        this.destAsset = destAsset;
        this.srcPlatform = srcPlatform;
        this.destPlatform = destPlatform;
        this.fee = fee;
        this.feeAsset = feeAsset;
    }

    /**
     * Creates a new empty platform instance
     * Need for the bean creation of Spring because it uses the setter and getter methods
     */
    public Transaction() {
    }

    /**
     * Getter for the id of the transaction
     *
     * @return Long
     * The id of the transaction
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for the id of the transaction
     *
     * @param id The identifier of transaction
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the date and time of the transaction
     *
     * @return LocalDateTime
     * The date and time of the transaction
     */
    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    /**
     * Setter for the date and time of the transaction
     *
     * @param dateAndTime The date and time of transaction
     */
    public void setDateAndTime(LocalDateTime dateAndTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        dateAndTime.format(formatter);

        this.dateAndTime = dateAndTime;
    }

    /**
     * Getter for the source amount of the transaction
     *
     * @return BigDecimal
     * The source amount of the transaction
     */
    public BigDecimal getSrcAmount() {
        return srcAmount;
    }

    /**
     * Getter for the source amount of the transaction
     *
     * @return BigDecimal
     * The source amount of the transaction
     */

    public String getSrcAmountToString() {

        return df.format(srcAmount.stripTrailingZeros());
    }

    /**
     * Setter for the source amount of the transaction
     *
     * @param srcAmount The source amount of transaction
     */
    public void setSrcAmount(BigDecimal srcAmount) {
        this.srcAmount = srcAmount;
    }

    /**
     * Getter for the source asset of the transaction
     *
     * @return Asset
     * The source asset of the transaction
     * @see Asset
     */
    public Asset getSrcAsset() {
        return srcAsset;
    }

    /**
     * Setter for the source asset of the transaction
     *
     * @param srcAsset The source asset of transaction
     * @see Asset
     */
    public void setSrcAsset(Asset srcAsset) {
        this.srcAsset = srcAsset;
    }

    /**
     * Getter for the destination amount of the transaction
     *
     * @return BigDecimal
     * The destination amount of the transaction
     */
    public BigDecimal getDestAmount() {
        return destAmount;
    }

    public String getDestAmountToString() {

        return df.format(destAmount.stripTrailingZeros());
    }

    /**
     * Setter for the destination amount of the transaction
     *
     * @param destAmount The destination amount of transaction
     */
    public void setDestAmount(BigDecimal destAmount) {
        this.destAmount = destAmount;
    }

    /**
     * Getter for the destination asset of the transaction
     *
     * @return Asset
     * The destination asset of the transaction
     * @see Asset
     */
    public Asset getDestAsset() {
        return destAsset;
    }

    /**
     * Setter for the destination asset of the transaction
     *
     * @param destAsset The destination asset of transaction
     * @see Asset
     */
    public void setDestAsset(Asset destAsset) {
        this.destAsset = destAsset;
    }

    /**
     * Getter for the source platform of the transaction
     *
     * @return Platform
     * The source platform of the transaction
     * @see Platform
     */
    public Platform getSrcPlatform() {
        return srcPlatform;
    }

    /**
     * Setter for the source platform of the transaction
     *
     * @param srcPlatform The source platform asset of transaction
     * @see Platform
     */
    public void setSrcPlatform(Platform srcPlatform) {
        this.srcPlatform = srcPlatform;
    }

    /**
     * Getter for the destination platform of the transaction
     *
     * @return Platform
     * The destination platform of the transaction
     * @see Platform
     */
    public Platform getDestPlatform() {
        return destPlatform;
    }

    /**
     * Setter for the destination platform of the transaction
     *
     * @param destPlatform The destination platform asset of transaction
     * @see Platform
     */
    public void setDestPlatform(Platform destPlatform) {
        this.destPlatform = destPlatform;
    }

    /**
     * Getter for the fee amount of the transaction
     *
     * @return BigDecimal
     * The fee amount m of the transaction
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * Setter for the fee amount of the transaction
     *
     * @param fee The fee amount asset of transaction
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * Getter for the fee asset of the transaction
     *
     * @return Platform
     * The fee asset of the transaction
     * @see Asset
     */
    public Asset getFeeAsset() {
        return feeAsset;
    }

    /**
     * Setter for the fee asset of the transaction
     *
     * @param feeAsset The fee asset of transaction
     * @see Asset
     */
    public void setFeeAsset(Asset feeAsset) {
        this.feeAsset = feeAsset;
    }

    /**
     * Getter for the notes of the transaction
     *
     * @return String
     * The notes of the transaction
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Setter for the notes of the transaction
     *
     * @param notes The notes of transaction
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Gets the date and time of the transaction as a string
     *
     * @return String
     * The date and time of the transaction as a string
     */
    public String getStringDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MMM yyyy HH:mm:ss");
        return dateAndTime.format(formatter);
    }

}
