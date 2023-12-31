package com.example.weatherforecast.service;

import com.example.weatherforecast.api.MedTermForecastApiClient;
import com.example.weatherforecast.domain.medtermforecast.MedTermLandForecast;
import com.example.weatherforecast.domain.medtermforecast.MedTermLandForecastRepository;
import com.example.weatherforecast.domain.region.MedTermLandRegionRepository;
import com.example.weatherforecast.util.MedTermForecastUtil;
import com.example.weatherforecast.util.mapper.Mapper;
import com.example.weatherforecast.web.dto.MedTermForecastRequestDto;
import com.example.weatherforecast.web.dto.MedTermLandForecastAfterAWeekJsonResponseDto;
import com.example.weatherforecast.web.dto.MedTermLandForecastResponseDto;
import com.example.weatherforecast.web.dto.MedTermLandForecastWithinAWeekJsonResponseDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedTermLandForecastService {
    private final MedTermForecastApiClient apiClient;
    private final MedTermLandForecastRepository repository;
    private final MedTermLandRegionRepository regionRepository;

    private final MedTermForecastUtil util;

    private static final Logger LOGGER = LoggerFactory.getLogger(MedTermLandForecastService.class);

    //TODO
    //중복데이터를 받는 경우 업데이트 하게끔 바꾸기
    public void saveDailyMedTermForecastDataList(String regId) throws ParseException {

        String baseDateTime = util.getBaseDateTime();

        MedTermLandForecastResponseDto responseDto = apiClient.getDailyMedTermLandForecastData(regId, baseDateTime);

        //요청인자로 데이터 조회
        Optional<MedTermLandForecast> existingForecast = repository.findByRegIdAndBaseDateTime(
                regId, baseDateTime);

        //기존에 데이터가 존재하면 업데이트
        if(existingForecast.isPresent()){
            updateExistingForecast(Mapper.medTermLandForecastToDto(existingForecast.get()));
            LOGGER.info("data updated to the database");
        } else {
            repository.save(responseDto.toEntity());
            LOGGER.info("data saved to the database");
        }
    }

    public String getMedTermForecastAfterDayNumber(MedTermForecastRequestDto requestDto){
        if(requestDto.getDayNumber()<=7) {
            Optional<MedTermLandForecastWithinAWeekJsonResponseDto> withinAWeekResponseDto = repository.findDynamicForecastWithinAWeekByRegIdAndBaseDate(requestDto, util.getBaseDateTime());
            LOGGER.info("BaseDate: {}", util.getBaseDateTime());
            LOGGER.info("Retrieved data: {}", withinAWeekResponseDto.toString());
            if (withinAWeekResponseDto.isPresent()) {
                LOGGER.info("Within a week forecast data successfully retrieved from the DB");
                return generateMedTermForecastDescriptionOfAfterDayNumber(requestDto, withinAWeekResponseDto.get());
            } else {
                LOGGER.error("Couldn't retrieved the within a week data");
                return "couldn't retrieved the data";
            }
        } else {
            Optional<MedTermLandForecastAfterAWeekJsonResponseDto> AfterAWeekResponseDto = repository.findDynamicForecastAfterAWeekByRegIdAndBaseDate(requestDto, util.getBaseDateTime());
            if (AfterAWeekResponseDto.isPresent()) {
                LOGGER.info("After a week forecast data successfully retrieved from the DB");
                return generateMedTermForecastDescriptionOfAfterDayNumber(requestDto, AfterAWeekResponseDto.get());
            } else {
                LOGGER.error("Couldn't retrieved the after a week data");
                return "couldn't retrieved the data";
            }
        }
    }


    public String generateMedTermForecastDescriptionOfAfterDayNumber(MedTermForecastRequestDto requestDto,
                                                                     Object responseDto){
        JSONObject json = new JSONObject();
        //행정구역 구하기
        String regionStr = regionRepository.findByRegId(requestDto.getRegId());
        LOGGER.info("예보 지역: {}", regionStr);
        Integer dayNumber = requestDto.getDayNumber();

        json.put("region", regionStr);
        json.put("baseDate", util.getBaseDate());
        json.put("baseTime", util.determinePivotTimes());
        json.put("dayNumber", dayNumber);

        if (responseDto instanceof MedTermLandForecastWithinAWeekJsonResponseDto) {
            MedTermLandForecastWithinAWeekJsonResponseDto dto = (MedTermLandForecastWithinAWeekJsonResponseDto) responseDto;
            json.put("rnStAm", dto.getRnStAm());
            json.put("rnStPm", dto.getRnStPm());
            json.put("wfAm", dto.getWfAm());
            json.put("wfPm", dto.getWfPm());
        } else if (responseDto instanceof MedTermLandForecastAfterAWeekJsonResponseDto) {
            MedTermLandForecastAfterAWeekJsonResponseDto dto = (MedTermLandForecastAfterAWeekJsonResponseDto) responseDto;
            json.put("rnSt", dto.getRnSt());
            json.put("wf", dto.getWf());
        }

        LOGGER.info("retrieved data : {}", json);
        return json.toString();
    }


    private void updateExistingForecast(MedTermLandForecastResponseDto responseDto) {
        repository.updateByRegIdAndBaseDateTime(
                responseDto.getRegId(),
                responseDto.getBaseDateTime(),
                responseDto.getRnSt3Am(),
                responseDto.getRnSt3Pm(),
                responseDto.getWf3Am(),
                responseDto.getWf3Pm(),
                responseDto.getRnSt4Am(),
                responseDto.getRnSt4Pm(),
                responseDto.getWf4Am(),
                responseDto.getWf4Pm(),
                responseDto.getRnSt5Am(),
                responseDto.getRnSt5Pm(),
                responseDto.getWf5Am(),
                responseDto.getWf5Pm(),
                responseDto.getRnSt6Am(),
                responseDto.getRnSt6Pm(),
                responseDto.getWf6Am(),
                responseDto.getWf6Pm(),
                responseDto.getRnSt7Am(),
                responseDto.getRnSt7Pm(),
                responseDto.getWf7Am(),
                responseDto.getWf7Pm(),
                responseDto.getRnSt8(),
                responseDto.getWf8(),
                responseDto.getRnSt9(),
                responseDto.getWf9(),
                responseDto.getRnSt10(),
                responseDto.getWf10());
        }

    }
