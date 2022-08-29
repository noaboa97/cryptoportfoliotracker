package com.cryptoportfoliotracker.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="transaction_id")
    private UUID id;
    //@NotNull
    private Date dateAndTime;
    //@NotNull
    private BigDecimal srcAmount;
    //@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset",
            foreignKey = @javax.persistence.ForeignKey(name = "srcasset_fk"))
    private CryptoAsset srcAsset;
    //@NotNull
    private BigDecimal destAmount;
    //@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset",
            foreignKey = @javax.persistence.ForeignKey(name = "destasset_fk"),insertable = false,updatable = false)
    private CryptoAsset destAsset;
    //@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform",
            foreignKey = @javax.persistence.ForeignKey(name = "srcplatform_fk"))
    private Platform srcPlatform;
    //@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform",
            foreignKey = @javax.persistence.ForeignKey(name = "destplatform_fk"),insertable = false,updatable = false)
    private Platform destPlatform;
    //@NotNull
    private BigDecimal fees;
    //@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset",
            foreignKey = @javax.persistence.ForeignKey(name = "feeasset_fk"),insertable = false,updatable = false)
    private CryptoAsset feeAsset;
    private String notes;

    public Transaction(/*UUID id,*/ Date dateAndTime, BigDecimal srcAmount, CryptoAsset srcAsset, BigDecimal destAmount, CryptoAsset destAsset, Platform srcPlatform, Platform destPlatform) {
        //this.id = id;
        this.dateAndTime = dateAndTime;
        this.srcAmount = srcAmount;
        this.srcAsset = srcAsset;
        this.destAmount = destAmount;
        this.destAsset = destAsset;
        this.srcPlatform = srcPlatform;
        this.destPlatform = destPlatform;
    }

    public Transaction() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public BigDecimal getSrcAmount() {
        return srcAmount;
    }

    public void setSrcAmount(BigDecimal srcAmount) {
        this.srcAmount = srcAmount;
    }

    public CryptoAsset getSrcAsset() {
        return srcAsset;
    }

    public void setSrcAsset(CryptoAsset srcAsset) {
        this.srcAsset = srcAsset;
    }

    public BigDecimal getDestAmount() {
        return destAmount;
    }

    public void setDestAmount(BigDecimal destAmount) {
        this.destAmount = destAmount;
    }

    public CryptoAsset getDestAsset() {
        return destAsset;
    }

    public void setDestAsset(CryptoAsset destAsset) {
        this.destAsset = destAsset;
    }

    public Platform getSrcPlatform() {
        return srcPlatform;
    }

    public void setSrcPlatform(Platform srcPlatform) {
        this.srcPlatform = srcPlatform;
    }

    public Platform getDestPlatform() {
        return destPlatform;
    }

    public void setDestPlatform(Platform destPlatform) {
        this.destPlatform = destPlatform;
    }

    public BigDecimal getFees() {
        return fees;
    }

    public void setFees(BigDecimal fees) {
        this.fees = fees;
    }

    public CryptoAsset getFeeAsset() {
        return feeAsset;
    }

    public void setFeeAsset(CryptoAsset feeAsset) {
        this.feeAsset = feeAsset;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
