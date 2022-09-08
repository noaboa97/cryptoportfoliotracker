package com.cryptoportfoliotracker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="transaction_id")
    private Long id;
    @NotNull
    private LocalDateTime dateAndTime;
    @NotNull
    @Column(precision = 30, scale = 10)
    private BigDecimal srcAmount;
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "srcasset",
            foreignKey = @javax.persistence.ForeignKey(name = "srcasset_fk")/*,insertable = false,updatable = false*/)
    private Asset srcAsset;
    @NotNull
    @Column(precision = 30, scale = 10)
    private BigDecimal destAmount;
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "destasset",
            foreignKey = @javax.persistence.ForeignKey(name = "destasset_fk")/*,insertable = false,updatable = false*/)
    private Asset destAsset;
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "srcplatform",
            foreignKey = @javax.persistence.ForeignKey(name = "srcplatform_fk")/*,insertable = false,updatable = false*/)
    private Platform srcPlatform;
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "destplatform",
            foreignKey = @javax.persistence.ForeignKey(name = "destplatform_fk")/*,insertable = false,updatable = false*/)
    private Platform destPlatform;

    @Column(precision = 30, scale = 10)
    private BigDecimal fee;

    @ManyToOne()
    @JoinColumn(name = "feeasset",
            foreignKey = @javax.persistence.ForeignKey(name = "feeasset_fk")/*,insertable = false,updatable = false*/)
    private Asset feeAsset;
    private String notes;

    public Transaction(LocalDateTime dateAndTime, BigDecimal srcAmount, Asset srcAsset, BigDecimal destAmount, Asset destAsset, Platform srcPlatform, Platform destPlatform,BigDecimal fee,Asset feeAsset) {
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
        return fee;
    }

    public void setFees(BigDecimal fee) {
        this.fee = fee;
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

    public void getTransaction() {

        System.out.println("Date "+dateAndTime);
        System.out.println("Src Amount "+srcAmount);
        System.out.println("Src Asset "+srcAsset);
        System.out.println("Dest Amount "+destAmount);
        System.out.println("Src Asset "+destAsset);
        System.out.println("Src Platform "+srcPlatform);
        System.out.println("Dest Amount "+destPlatform);
        System.out.println("Fee "+fee);
        System.out.println("Fee Asset "+feeAsset);
        System.out.println("Notes "+notes);

    }
}
