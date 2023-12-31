package com.example.weatherforecast.service;

import com.example.weatherforecast.api.MedTermForecastApiClient;
import com.example.weatherforecast.domain.medtermforecast.MedTermTempForecast;
import com.example.weatherforecast.domain.medtermforecast.MedTermTempForecastRepository;
import com.example.weatherforecast.domain.region.MedTermTempRegionRepository;
import com.example.weatherforecast.util.MedTermForecastUtil;
import com.example.weatherforecast.util.mapper.Mapper;
import com.example.weatherforecast.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedTermTempForecastService {
    private final MedTermForecastApiClient apiClient;
    private final MedTermTempForecastRepository repository;
    private final MedTermTempRegionRepository regionRepository;

    private final MedTermForecastUtil util;

    private static final Logger LOGGER = LoggerFactory.getLogger(MedTermTempForecastService.class);


    //TODO
    //중복데이터를 받는 경우 업데이트 하게끔 바꾸기
    public void saveDailyMedTermTempForecastDataList(String regId) throws ParseException {

        String baseDateTime = util.getBaseDateTime();

        MedTermTempForecastResponseDto responseDto = apiClient.getDailyMedTermTempForecastData(regId, util.getBaseDateTime());

        //요청인자로 데이터 조회
        Optional<MedTermTempForecast> existingForecast = repository.findByRegIdAndBaseDateTime(
                regId, baseDateTime);

        //기존에 데이터가 존재하면 업데이트
        if(existingForecast.isPresent()){
            updateExistingForecast(Mapper.medTermTempForecastToDto(existingForecast.get()));
            LOGGER.info("TEMP: data updated to the database");
        } else {
            repository.save(responseDto.toEntity());
            LOGGER.info("TEMP: data saved to the database");
        }
    }

    public String getMedTermTempForecastByDayNumber(MedTermForecastRequestDto requestDto){
        Optional<MedTermTempForecastJsonResponseDto> responseDto = repository.findDynamicTempForecastByRegIdAndBaseDateTime(requestDto, util.getBaseDateTime());
        if(responseDto.isPresent()){
            LOGGER.info("BaseDate: {}", util.getBaseDateTime());
            LOGGER.info("Retrieved data: {}", requestDto.toString());
            return generateMedTermTempForecastDescription(requestDto, responseDto.get());
        } else {
            LOGGER.error("Couldn't retrieved the med-term temp forecast data");
            return "couldn't retrieved the data";
        }

    }

    public String generateMedTermTempForecastDescription(MedTermForecastRequestDto requestDto,
                                                         MedTermTempForecastJsonResponseDto jsonResponseDto){

        JSONObject json = new JSONObject();
        //행정구역 구하기
        String regionStr = regionRepository.findByRegId(requestDto.getRegId());
        LOGGER.info("예보 지역: {}", regionStr);
        Integer dayNumber = requestDto.getDayNumber();

        json.put("region", regionStr);
        json.put("baseDate", util.getBaseDate());
        json.put("baseTime", util.determinePivotTimes());
        json.put("dayNumber", dayNumber);

        json.put("taMax", jsonResponseDto.getTaMax());
        json.put("taMaxHigh", jsonResponseDto.getTaMaxHigh());
        json.put("taMaxLow", jsonResponseDto.getTaMaxLow());
        json.put("taMin", jsonResponseDto.getTaMin());
        json.put("taMinHigh", jsonResponseDto.getTaMinHigh());
        json.put("taMinLow", jsonResponseDto.getTaMinLow());

        LOGGER.info("retrieved data : {}", json);
        return json.toString();
    }

    private void updateExistingForecast(MedTermTempForecastResponseDto responseDto){
        repository.updateTempForecastByRegIdAndBaseDateTime(
                responseDto.getRegId(),
                responseDto.getBaseDateTime(),
                responseDto.getTaMax3(),
                responseDto.getTaMax3High(),
                responseDto.getTaMax3Low(),
                responseDto.getTaMin3(),
                responseDto.getTaMin3High(),
                responseDto.getTaMin3Low(),
                responseDto.getTaMax4(),
                responseDto.getTaMax4High(),
                responseDto.getTaMax4Low(),
                responseDto.getTaMin4(),
                responseDto.getTaMin4High(),
                responseDto.getTaMin4Low(),
                responseDto.getTaMax5(),
                responseDto.getTaMax5High(),
                responseDto.getTaMax5Low(),
                responseDto.getTaMin5(),
                responseDto.getTaMin5High(),
                responseDto.getTaMin5Low(),
                responseDto.getTaMax6(),
                responseDto.getTaMax6High(),
                responseDto.getTaMax6Low(),
                responseDto.getTaMin6(),
                responseDto.getTaMin6High(),
                responseDto.getTaMin6Low(),
                responseDto.getTaMax7(),
                responseDto.getTaMax7High(),
                responseDto.getTaMax7Low(),
                responseDto.getTaMin7(),
                responseDto.getTaMin7High(),
                responseDto.getTaMin7Low(),
                responseDto.getTaMax8(),
                responseDto.getTaMax8High(),
                responseDto.getTaMax8Low(),
                responseDto.getTaMin8(),
                responseDto.getTaMin8High(),
                responseDto.getTaMin8Low(),
                responseDto.getTaMax9(),
                responseDto.getTaMax9High(),
                responseDto.getTaMax9Low(),
                responseDto.getTaMin9(),
                responseDto.getTaMin9High(),
                responseDto.getTaMin9Low(),
                responseDto.getTaMax10(),
                responseDto.getTaMax10High(),
                responseDto.getTaMax10Low(),
                responseDto.getTaMin10(),
                responseDto.getTaMin10High(),
                responseDto.getTaMin10Low()
        );
    }




}
