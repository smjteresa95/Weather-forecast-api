package com.example.weatherforecast.service;

import com.example.weatherforecast.api.UltraShortTermForecastApiClient;
import com.example.weatherforecast.domain.region.ShortTermRegionRepository;
import com.example.weatherforecast.domain.ultrashorttermforcast.UltraShortTermForecast;
import com.example.weatherforecast.domain.ultrashorttermforcast.UltraShortTermForecastRepository;
import com.example.weatherforecast.util.PrecipitationType;
import com.example.weatherforecast.util.SkyCondition;
import com.example.weatherforecast.util.mapper.Mapper;
import com.example.weatherforecast.web.dto.ShortTermRegionDto;
import com.example.weatherforecast.web.dto.UltraShortTermForecastRequestDto;
import com.example.weatherforecast.web.dto.UltraShortTermForecastResponseDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UltraShortTermForecastService {

    private final UltraShortTermForecastApiClient apiClient;
    private final UltraShortTermForecastRepository repository;
    private final ShortTermRegionRepository regionRepository;
    private final Mapper mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(UltraShortTermForecastService.class);

    //한 날짜에 해당하는 데이터 값의 리스트를 DB에 저장.
    @Transactional
    public void saveDailyForecastDataList(UltraShortTermForecastRequestDto requestDto) throws ParseException {

        List<UltraShortTermForecastResponseDto> responseDtoList = apiClient.getDailyUltraShortTermForecast(requestDto);

        for(UltraShortTermForecastResponseDto responseDto : responseDtoList){
            // BaseDate, BaseTime, FcstDate, FcstTime, RegionCode 이 일치하는 데이터가 존재하면 업데이트
            Optional<UltraShortTermForecast> existingForecast = repository.findByBaseDateAndBaseTimeAndFcstDateAndFcstTimeAndRegionCode(
                    responseDto.getBaseDate(),
                    responseDto.getBaseTime(),
                    responseDto.getFcstDate(),
                    responseDto.getFcstTime(),
                    responseDto.getRegionCode()
            );

            if(existingForecast.isPresent()){
                updateExistingForecast(mapper.ultraShortTermForecastToDto(existingForecast.get()));
                LOGGER.info("data updated to the database");
            } else {
                repository.save(responseDto.toEntity());
                LOGGER.info("data saved to the database");
            }
        }
    }

    @Transactional
    public String generateJsonDataForGivenFcstTime(UltraShortTermForecastRequestDto requestDto, String fcstTime){
        Optional<UltraShortTermForecast> forecastOptional = repository.findByRegionCodeAndBaseDateAndBaseTimeAndFcstTime(
                requestDto.getRegionCode(), requestDto.getBaseDate(), fcstTime
        );

        if (forecastOptional.isEmpty()) {
            LOGGER.error("No data found for the specified criteria");
            return "No data available";
        }

        UltraShortTermForecastResponseDto forecastDto = mapper.ultraShortTermForecastToDto(forecastOptional.get());
        return generateUltraShortTermForecastDescription(requestDto, forecastDto);
    }


    //DB에 저장된 값을 바탕으로 JSON형 문자열 만들기
    public String generateUltraShortTermForecastDescription(UltraShortTermForecastRequestDto requestDto, UltraShortTermForecastResponseDto responseDto){

        ShortTermRegionDto region = mapper.ShortTermRegionToDto(
                regionRepository.findRegionByCode(requestDto.getRegionCode()).orElseThrow(() -> new NoSuchElementException("Region not found")));

        // JSON 객체 생성
        JSONObject json = new JSONObject();
        json.put("level1", region.getLevel1()); //시
        json.put("level2", region.getLevel2()); //구
        json.put("level3", region.getLevel3()); //동/면/읍

        json.put("baseDate", responseDto.getBaseDate()); //기준일
        json.put("baseTime", responseDto.getBaseTime()); //기준시간
        json.put("fcstDate", responseDto.getFcstDate()); //발표일자
        json.put("fcstTime", responseDto.getFcstTime()); //발표시간

        int rn1Num = responseDto.getRn1().intValue();
        String pcpCode;
        if(rn1Num == 0) {
            pcpCode = "강수없음";
        } else pcpCode = responseDto.getRn1().toString();
        json.put("pcp", pcpCode); //1시간 강수량 (mm)

        String skyNum = String.valueOf(responseDto.getSky());
        String skyCode = SkyCondition.getDescriptionByCode(skyNum);
        json.put("sky", skyCode); //하늘상태

        json.put("reh", responseDto.getReh()); //습도 (%)
        json.put("uuu", responseDto.getUuu()); //풍속(동서성분) (m/s)
        json.put("vvv", responseDto.getVvv()); //풍속(남북성분) (m/s)
        json.put("vec", responseDto.getVec()); //풍향 (deg)
        json.put("wsd", responseDto.getWsd()); //풍속 (m/s)
        json.put("t1h", responseDto.getT1h()); //기온

        String ptyNum = String.valueOf(responseDto.getPty());
        String ptyCode = PrecipitationType.getDescriptionByCode(ptyNum);
        json.put("pty", ptyCode); //강수형태

        json.put("lgt", responseDto.getLgt()); //낙뢰

        LOGGER.info("parsed json data : {}", json);
        return json.toString();

    }

    private void updateExistingForecast(UltraShortTermForecastResponseDto responseDto){
        repository.updateUltraShortTermForecast(
                responseDto.getRegionCode(),
                responseDto.getBaseDate(),
                responseDto.getBaseTime(),
                responseDto.getFcstDate(),
                responseDto.getFcstTime(),
                responseDto.getRn1(),
                responseDto.getSky(),
                responseDto.getReh(),
                responseDto.getUuu(),
                responseDto.getVvv(),
                responseDto.getVec(),
                responseDto.getWsd(),
                responseDto.getT1h(),
                responseDto.getPty(),
                responseDto.getLgt()
        );
    }







}
