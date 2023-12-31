package com.example.weatherforecast.service;

import com.example.weatherforecast.domain.region.*;
import com.example.weatherforecast.util.RegionFileReader;
import com.example.weatherforecast.web.dto.MedTermRegionDto;
import com.example.weatherforecast.web.dto.ShortTermRegionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionDataService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegionDataService.class);

    private final ShortTermRegionRepository repository;
    private final MedTermLandRegionRepository medTermLandRegionRepository;
    private final MedTermTempRegionRepository medTermTempRegionRepository;

    private static String LANDPATH = "src\\main\\assets\\중기예보_중기육상예보구역코드.xlsx";
    private static String TEMPPATH = "src\\main\\assets\\중기예보_중기기온예보구역코드.xlsx";
    private static String LANDFORECAST = "land";
    private static String TEMPFORECAST = "temp";


    public RegionDataService(ShortTermRegionRepository repository, MedTermLandRegionRepository medTermLandRegionRepository, MedTermTempRegionRepository medTermTempRegionRepository) {
        this.repository = repository;
        this.medTermLandRegionRepository = medTermLandRegionRepository;
        this.medTermTempRegionRepository = medTermTempRegionRepository;
    }

    public void loadAndSaveShortTermRegionData(){
        List<ShortTermRegionDto> regionList = RegionFileReader.parseShortTermRegion();
        for(ShortTermRegionDto data: regionList){
            repository.save(data.toEntity());
        }
        LOGGER.info("Short-term region data saved to database");
    }

    public void loadAndSaveMedTermLandRegionData(){
        List<MedTermRegionDto> regionDtoList = RegionFileReader.parseMedTermRegion(LANDPATH);
        for(MedTermRegionDto data : regionDtoList){
            medTermLandRegionRepository.save((MedTermLandRegion) data.toEntity(LANDFORECAST));
        }
        LOGGER.info("Med-term land region data saved to database");
    }

    public void loadAndSaveMedTermTempRegionData(){
        List<MedTermRegionDto> regionDtoList = RegionFileReader.parseMedTermRegion(TEMPPATH);
        for(MedTermRegionDto data : regionDtoList){
            medTermTempRegionRepository.save((MedTermTempRegion) data.toEntity(TEMPFORECAST));
        }
        LOGGER.info("Med-term land region data saved to database");
    }
}
