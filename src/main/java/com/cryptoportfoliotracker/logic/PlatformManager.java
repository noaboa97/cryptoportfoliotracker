package com.cryptoportfoliotracker.logic;

import com.cryptoportfoliotracker.entities.Platform;
import com.cryptoportfoliotracker.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformManager {
    //methods accessed by ui
    @Autowired
    private final PlatformRepository platformRepository;

    public PlatformManager(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }


    public List<Platform> findAllPlatforms() {
        return platformRepository.findAll();
    }
}
