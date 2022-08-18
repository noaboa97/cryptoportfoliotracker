package com.cryptoportfoliotracker.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

//@Entity
public class Transaction {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date dateAndTime;
    private BigDecimal srcAmount;
    private Asset srcAsset;
    private BigDecimal destAmount;
    private Asset destAsset;
    private Platform srcPlatform;
    private Platform destPlatform;
    private BigDecimal fees;
    private Asset feeAsset;
    private String notes;

    public Transaction(int id, Date dateAndTime, BigDecimal srcAmount, Asset srcAsset, BigDecimal destAmount, Asset destAsset, Platform srcPlatform, Platform destPlatform) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.srcAmount = srcAmount;
        this.srcAsset = srcAsset;
        this.destAmount = destAmount;
        this.destAsset = destAsset;
        this.srcPlatform = srcPlatform;
        this.destPlatform = destPlatform;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Asset getSrcAsset() {
        return srcAsset;
    }

    public void setSrcAsset(Asset srcAsset) {
        this.srcAsset = srcAsset;
    }

    public BigDecimal getDestAmount() {
        return destAmount;
    }

    public void setDestAmount(BigDecimal destAmount) {
        this.destAmount = destAmount;
    }

    public Asset getDestAsset() {
        return destAsset;
    }

    public void setDestAsset(Asset destAsset) {
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

    public Asset getFeeAsset() {
        return feeAsset;
    }

    public void setFeeAsset(Asset feeAsset) {
        this.feeAsset = feeAsset;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
