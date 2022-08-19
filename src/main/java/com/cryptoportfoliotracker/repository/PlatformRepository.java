package com.cryptoportfoliotracker.repository;

import com.cryptoportfoliotracker.entities.Platform;

import java.util.ArrayList;

public class PlatformRepository {
    private ArrayList<Platform> PlatformList;

    public PlatformRepository(ArrayList<Platform> List) {
        PlatformList = List;
    }

    public PlatformRepository() {
    }

    public ArrayList<Platform> getPlatformList() {
        return PlatformList;
    }

    public void addPlatform(Platform Platform) {
        PlatformList.add(Platform);
    }

}