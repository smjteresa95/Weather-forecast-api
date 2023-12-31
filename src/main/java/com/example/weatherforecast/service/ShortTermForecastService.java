package com.example.weatherforecast.service;

import com.example.weatherforecast.api.ShortTermForecastApiClient;
import com.example.weatherforecast.domain.region.ShortTermRegionRepository;
import com.example.weatherforecast.domain.shorttermforecast.ShortTermForecast;
import com.example.weatherforecast.domain.shorttermforecast.ShortTermForecastRepository;
import com.example.weatherforecast.util.PrecipitationType;
import com.example.weatherforecast.util.SkyCondition;
import com.example.weatherforecast.util.mapper.Mapper;
import com.example.weatherforecast.web.dto.ShortTermForecastRequestDto;
import com.example.weatherforecast.web.dto.ShortTermForecastResponseDto;
import com.example.weatherforecast.web.dto.ShortTermRegionDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShortTermForecastService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShortTermForecastService.class);

    private final ShortTermForecastApiClient apiClient;
    private final ShortTermForecastRepository repository;
    private final ShortTermRegionRepository regionRepository;
    private final Mapper mapper;

    //단기예보 JSON데이터를 받아서 각 코드에 해당 하는 값을 DB에 저장
    @Transactional
    public void saveShortTermForecastData(ShortTermForecastRequestDto requestDto) {
        try {
            // API 호출하여 JSON 데이터 받아오기
            String jsonData = apiClient.callShortTermForecastApi(requestDto);

            //JSON데이터 잘 받아왔는지 확인
            if (jsonData == null || jsonData.isEmpty()) {
                LOGGER.error("Received empty JSON data from the API");
            } else {
                LOGGER.info("Received JSON data: {}", jsonData);
            }

            // JSON 데이터를 DTO 객체로 파싱
            ShortTermForecastResponseDto responseDto = apiClient.parseShortTermForecastJsonData(jsonData);
            if (responseDto == null) {
                LOGGER.error("Parsed DTO is null");
                return;
            }

            responseDto.setRegionCode(String.valueOf(requestDto.getRegionCode()));

            //기존 동일한 데이터가 존재하는 경우 id값 받아오기.
            Optional<Long> forecastIdOptional = repository.findIdByBaseDateAndBaseTimeAndRegionCode(
                    responseDto.getBaseDate(), responseDto.getBaseTime(), responseDto.getRegionCode()
            );

            ShortTermForecastResponseDto existingForecast;
            if (forecastIdOptional.isPresent()) {
                // 기존 데이터의 id가 존재하는 경우, 해당 객체를 가져오기
                existingForecast = mapper.ShortTermForecastToDto(
                        repository.findById(forecastIdOptional.get()).orElse(new ShortTermForecast()));
                //업데이트
                updateShortTermForecast(existingForecast);
                LOGGER.info("shortTerm data updated from database");
            } else {
                repository.save(responseDto.toEntity()); //저장
                LOGGER.info("Short term data saved to database");
            }

        } catch (Exception e) {
            LOGGER.error("Error occurred while saving short term forecast data: ", e);
            throw new RuntimeException("Fail to save shortTerm data to the database: " + e.getMessage());
        }
    }

    //DB에 저장된 단기예보 데이터 JSON형식으로 받아오기
    public String getShortTermForecastDescription(ShortTermForecastRequestDto requestDto){
        //저장 된 값 받아오기
        ShortTermForecastResponseDto forecast = mapper.ShortTermForecastToDto(repository.findByMatchingCriteria(
                requestDto.getBaseDate(), requestDto.getBaseTime(), requestDto.getRegionCode()
        ).get());

        //받아온 값으로 JSON 문자열 만들어 반환
        return generateShorTermForecastDescription(requestDto, forecast);
    }


    //DB에 저장된 값을 바탕으로 JSON형 문자열 만들기
    public String generateShorTermForecastDescription(ShortTermForecastRequestDto requestDto, ShortTermForecastResponseDto responseDto){

        ShortTermRegionDto region = mapper.ShortTermRegionToDto(
                regionRepository.findRegionByCode(requestDto.getRegionCode()).orElseThrow(() -> new NoSuchElementException("Region not found")));

        // JSON 객체 생성
        JSONObject json = new JSONObject();
        json.put("시", region.getLevel1());
        json.put("구", region.getLevel2());
        json.put("동/면/읍", region.getLevel3());

        json.put("발표일자", responseDto.getBaseDate());
        json.put("발표시각", responseDto.getBaseTime());
        json.put("발표일자", responseDto.getFcstDate());
        json.put("발표시각", responseDto.getFcstTime());

        json.put("강수확률 (%)", responseDto.getPOP());

        int ptyNum = responseDto.getPTY().intValue();
        String ptyCode = PrecipitationType.getDescriptionByCode(ptyNum);
        json.put("강수형태", ptyCode);

        int pcpNum = responseDto.getPCP().intValue();
        String pcpCode;
        if(pcpNum == 0) {
            pcpCode = "강수없음";
        } else pcpCode = responseDto.getPCP().toString();
        json.put("1시간 강수량 (mm)", pcpCode);

        json.put("습도 (%)", responseDto.getREH());
        json.put("1시간 신적설 (cm)", responseDto.getSNO());

        int skyNum =  responseDto.getSKY().intValue();
        String skyCode = SkyCondition.getDescriptionByCode(skyNum);
        json.put("하늘상태", skyCode);

        json.put("1시간 기온 (℃)", responseDto.getTMP());
        json.put("일 최저기온 (℃)", responseDto.getTMN());
        json.put("일 최고기온 (℃)", responseDto.getTMX());
        json.put("풍속(동서성분) (m/s)", responseDto.getUUU());
        json.put("풍속(남북성분) (m/s)", responseDto.getVVV());
        json.put("파고 (M)", responseDto.getWAV());
        json.put("풍향 (deg)", responseDto.getVEC());
        json.put("풍속 (m/s)", responseDto.getWSD());

        return json.toString();

    }

    private void updateShortTermForecast(ShortTermForecastResponseDto responseDto) {
        repository.updateShortTermForecast(
                responseDto.getBaseDate(),
                responseDto.getBaseTime(),
                responseDto.getRegionCode(),
                responseDto.getFcstDate(),
                responseDto.getFcstTime(),
                responseDto.getPCP(),
                responseDto.getSKY(),
                responseDto.getREH(),
                responseDto.getUUU(),
                responseDto.getVVV(),
                responseDto.getVEC(),
                responseDto.getWSD(),
                responseDto.getPOP(),
                responseDto.getPTY(),
                responseDto.getSNO(),
                responseDto.getTMP(),
                responseDto.getTMN(),
                responseDto.getTMX(),
                responseDto.getWAV());
    }

}
