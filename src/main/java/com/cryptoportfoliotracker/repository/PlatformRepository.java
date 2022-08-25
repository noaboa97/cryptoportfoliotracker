package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.Platform;

import java.util.ArrayList;
import java.util.List;

public class PlatformRepository {
    private List<Platform> PlatformList = new ArrayList<>();

    public PlatformRepository(List<Platform> List) {
        this.PlatformList = List;
    }

    public PlatformRepository() {
    }

    public List<Platform> getPlatformList() {
        return this.PlatformList;
    }

    public void addPlatform(Platform Platform) {
        this.PlatformList.add(Platform);
    }

}