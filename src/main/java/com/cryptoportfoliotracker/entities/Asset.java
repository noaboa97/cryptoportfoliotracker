package com.cryptoportfoliotracker.entities;

import com.cryptoportfoliotracker.logic.CptService;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
public abstract class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="asset_id")
    private Long id;
    //@NotNull
    private String fullname;
    //@NotNull
    private String shortname;
    //@NotNull
    @ManyToOne(/*fetch = FetchType.LAZY*/)
    @JoinColumn(name = "platform",
            foreignKey = @javax.persistence.ForeignKey(name = "platform_fk"))
    private Platform platform;

    public Asset(/*Long id,*/ String fullname, String shortname, Platform platform) {
        //this.id = id;
        this.fullname = fullname;
        this.shortname = shortname;
        this.platform = platform;
    }

    public Asset() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public static class FiatAsset {

        public FiatAsset() {
        }

    }


    public BigDecimal getInvestedCapitalFiat(CptService service) {

        BigDecimal investedCapitalFiat = new BigDecimal("0");

        List<Transaction> list = service.findBySrcAsset(this);
        for ( Transaction t : list){

            // wenn dest assset == this
            investedCapitalFiat.add(t.getSrcAmount());

        }
        investedCapitalFiat = investedCapitalFiat;
        return investedCapitalFiat;
    }


    public abstract BigDecimal getInvestedCapitalCrypto(CptService service);

    public abstract BigDecimal getCurrentValueFiat(CptService service);
    @Override
    public String toString(){
        return shortname;
    }
}
