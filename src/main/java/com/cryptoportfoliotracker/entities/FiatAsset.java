package com.cryptoportfoliotracker.entities;

import com.cryptoportfoliotracker.logic.CptService;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class FiatAsset extends Asset{

    private BigDecimal investedCapitalFiat; // stores the invested money in fiat
    public FiatAsset( String fullname, String shortname, Platform platform) {
        super( fullname, shortname, platform);
    }

    public FiatAsset() {
        super();
    }

    public BigDecimal getInvestedCapitalCrypto(CptService service){

        return null;
    };

    public BigDecimal getCurrentValueFiat(CptService service){return null;};


}
