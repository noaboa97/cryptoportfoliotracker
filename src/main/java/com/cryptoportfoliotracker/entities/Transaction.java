package com.cryptoportfoliotracker.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="transaction_id")
    private Long id;
    //@NotNull
    private LocalDateTime dateAndTime;
    //@NotNull
    private BigDecimal srcAmount;
    //@NotNull
    @ManyToOne(/*fetch = FetchType.LAZY*/)
    @JoinColumn(name = "srcasset",
            foreignKey = @javax.persistence.ForeignKey(name = "srcasset_fk")/*,insertable = false,updatable = false*/)
    private CryptoAsset srcAsset;
    //@NotNull
    private BigDecimal destAmount;
    //@NotNull
    @ManyToOne(/*fetch = FetchType.LAZY*/)
    @JoinColumn(name = "destasset",
            foreignKey = @javax.persistence.ForeignKey(name = "destasset_fk")/*,insertable = false,updatable = false*/)
    private CryptoAsset destAsset;
    //@NotNull
    @ManyToOne(/*fetch = FetchType.LAZY*/)
    @JoinColumn(name = "srcplatform",
            foreignKey = @javax.persistence.ForeignKey(name = "srcplatform_fk")/*,insertable = false,updatable = false*/)
    private Platform srcPlatform;
    //@NotNull
    @ManyToOne(/*fetch = FetchType.LAZY*/)
    @JoinColumn(name = "destplatform",
            foreignKey = @javax.persistence.ForeignKey(name = "destplatform_fk")/*,insertable = false,updatable = false*/)
    private Platform destPlatform;
    //@NotNull
    private BigDecimal fee;
    //@NotNull
    @ManyToOne(/*fetch = FetchType.LAZY*/)
    @JoinColumn(name = "feeasset",
            foreignKey = @javax.persistence.ForeignKey(name = "feeasset_fk")/*,insertable = false,updatable = false*/)
    private CryptoAsset feeAsset;
    private String notes;

    public Transaction(LocalDateTime dateAndTime, BigDecimal srcAmount, CryptoAsset srcAsset, BigDecimal destAmount, CryptoAsset destAsset, Platform srcPlatform, Platform destPlatform) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
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
        return fee;
    }

    public void setFees(BigDecimal fee) {
        this.fee = fee;
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

    public void getTransaction() {

        System.out.println(dateAndTime);
        System.out.println(srcAmount);
        System.out.println(srcAsset);
        System.out.println(destAmount);
        System.out.println(destAsset);
        System.out.println(srcPlatform);
        System.out.println(destPlatform);
        System.out.println(fee);
        System.out.println(feeAsset);
        System.out.println(notes);

    }
}
