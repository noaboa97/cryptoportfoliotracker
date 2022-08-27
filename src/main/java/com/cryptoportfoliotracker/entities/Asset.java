package com.cryptoportfoliotracker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

//@Entity
public class Asset {
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //@NotEmpty
    private String fullname;
    //@NotEmpty
    private String shortname;
    //@NotEmpty
    private Platform platform;

    public Asset(int id, String fullname, String shortname, Platform platform) {
        this.id = id;
        this.fullname = fullname;
        this.shortname = shortname;
        this.platform = platform;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
