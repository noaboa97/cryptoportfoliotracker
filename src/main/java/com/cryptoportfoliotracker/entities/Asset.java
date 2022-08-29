package com.cryptoportfoliotracker.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="asset_id")
    private UUID id;
    //@NotNull
    private String fullname;
    //@NotNull
    private String shortname;
    //@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform",
            foreignKey = @javax.persistence.ForeignKey(name = "platform_fk"))
    private Platform platform;

    public Asset(/*UUID id,*/ String fullname, String shortname, Platform platform) {
        //this.id = id;
        this.fullname = fullname;
        this.shortname = shortname;
        this.platform = platform;
    }

    public Asset() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
}
