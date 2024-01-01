package com.example.weatherforecast.util;

import com.example.weatherforecast.service.RegionDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RegionDataInitializer implements CommandLineRunner {

    private final RegionDataService regionDataService;

    public RegionDataInitializer(RegionDataService regionDataService) {
        this.regionDataService = regionDataService;
    }

    @Override
    public void run(String... args) {
        regionDataService.loadAndSaveShortTermRegionData();
        regionDataService.loadAndSaveMedTermLandRegionData();
        regionDataService.loadAndSaveMedTermTempRegionData();
    }
}
