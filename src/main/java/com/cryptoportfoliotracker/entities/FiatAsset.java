package com.cryptoportfoliotracker.entities;

import com.cryptoportfoliotracker.logic.CptService;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class FiatAsset extends Asset{
    private boolean standard = false;
    public FiatAsset( String fullname, String shortname, Platform platform, boolean standard) {
        super( fullname, shortname, platform);
        this.standard = standard;
    }

    public FiatAsset() {
        super();
    }

    public boolean getStandard() {
        return standard;
    }

    public void setStandard(boolean standard) {
        this.standard = standard;
    }

    public BigDecimal getInvestedCapitalCrypto(CptService service){

        return null;
    };

    public BigDecimal getCurrentValueFiat(CptService service){return null;};


}
