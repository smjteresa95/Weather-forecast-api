package com.example.weatherforecast.api;

import com.example.weatherforecast.config.ApiConfig;
import com.example.weatherforecast.domain.region.ShortTermRegionRepository;
import com.example.weatherforecast.util.mapper.Mapper;
import com.example.weatherforecast.web.dto.ShortTermRegionDto;
import com.example.weatherforecast.web.dto.UltraShortTermForecastRequestDto;
import com.example.weatherforecast.web.dto.UltraShortTermForecastResponseDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UltraShortTermForecastApiClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(UltraShortTermForecastApiClient.class);

    private final ApiConfig apiConfig;
    private final Mapper mapper;
    private final RestTemplate restTemplate = new RestTemplate();

    private final ShortTermRegionRepository regionRepository;

    //날짜를 하나 설정 하고, date_time은 0030, 0630, 1230, 1830 네가지 얻어오게 되면
    //하루의 전체 데이터 값 가지고 오는 것이 가능 하다.
    List<String> dateTimeList = Arrays.asList("0030", "0630", "1230", "1830");

    public String callUltraShortTermForecastApi(String dateTime, UltraShortTermForecastRequestDto requestDto){
        //nx, ny를 행정구역코드에서 얻어오기
        ShortTermRegionDto regionDto = mapper.ShortTermRegionToDto(regionRepository.findRegionByCode(requestDto.getRegionCode()).get());
        String nx = String.valueOf(regionDto.getGridX());
        String ny = String.valueOf(regionDto.getGridY());

        String apiUrl = apiConfig.getUltraShortTermEndpoint();
        String apiKey = apiConfig.getShortTermKey();
        String dataType = apiConfig.getDataType();
        int numOfRows = 60;

        // URL 문자열 구성
        String urlStr = apiUrl + "?serviceKey=" + apiKey + "&dataType=" + dataType;
        urlStr += "&base_date=" + requestDto.getBaseDate();
        urlStr += "&base_time=" + dateTime;
        urlStr += "&numOfRows=" + numOfRows;
        urlStr += "&nx=" + nx;
        urlStr += "&ny=" + ny;

        // StringHttpMessageConverter를 UTF-8로 설정
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        ResponseEntity<String> response = restTemplate.exchange(
                urlStr,
                HttpMethod.GET,
                null,
                String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            LOGGER.info("API 응답: {}", response.getBody());
            return response.getBody();
        } else {
            LOGGER.error("API 호출 실패: {}", response.getStatusCode());
            return "API 호출 실패: " + response.getStatusCode();
        }

    }

    //지정한 날짜의 전체 데이터를 가진 dto 리스트 합치기.
    public List<UltraShortTermForecastResponseDto> getDailyUltraShortTermForecast(UltraShortTermForecastRequestDto requestDto) throws ParseException {
        List<UltraShortTermForecastResponseDto> responseDtoList = new ArrayList<>();

        for(String date : dateTimeList){
            String jsonData = callUltraShortTermForecastApi(date, requestDto);
            List<UltraShortTermForecastResponseDto> parsedList = parseUltraShortTermForecastJsonData(jsonData, requestDto);
            responseDtoList.addAll(parsedList);
        }

        return responseDtoList;
    }

    //하나의 JSON 데이터를 파싱하는 메서드
    //fcstTime 이 일치하는 값을 묶어서 하나의 dto로 만들고, 각 fcstTime에 해당하는 dto들의 list를 만든다.
    public List<UltraShortTermForecastResponseDto> parseUltraShortTermForecastJsonData(String jsonData, UltraShortTermForecastRequestDto requestDto) throws ParseException {

        if (jsonData == null) {
            LOGGER.error("Received null UltraShortTerm JSON data");
            return Collections.emptyList();
        }

        Map<String, UltraShortTermForecastResponseDto> groupedData = new HashMap<>();


        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(jsonData);

        //필요한 데이터 추출
        JSONObject parseResponse = (JSONObject) obj.get("response");
        JSONObject parseBody = (JSONObject) parseResponse.get("body");
        JSONObject parseItems = (JSONObject) parseBody.get("items");
        JSONArray parseItem = (JSONArray) parseItems.get("item");

        for (Object itemObj : parseItem) {
            JSONObject weather = (JSONObject) itemObj;
            //fcstTime을 기준으로 데이터 묶기
            String fcstTime = (String) weather.get("fcstTime");

            UltraShortTermForecastResponseDto dto = groupedData.computeIfAbsent(
                    fcstTime,
                    k -> new UltraShortTermForecastResponseDto()
            );
            String category = (String) weather.get("category");
            String value = (String) weather.get("fcstValue");

            String baseDate = (String) weather.get("baseDate");
            String baseTime = (String) weather.get("baseTime");
            String fcstDate = (String) weather.get("fcstDate");

            dto.setBaseDate(baseDate);
            dto.setBaseTime(baseTime);
            dto.setFcstDate(fcstDate);
            dto.setFcstTime(fcstTime);
            dto.setRegionCode(requestDto.getRegionCode());

            // 필드에 데이터 설정
            try {
                switch (category) {
                    case "T1H":
                        dto.setT1h(new BigDecimal(value));
                        break;
                    case "RN1":
                        String rn1Value = value.replace("mm", "").trim();
                        if (rn1Value.isEmpty() || rn1Value.equals("강수없음")) {
                            dto.setRn1(BigDecimal.ZERO);
                        } else {
                            dto.setRn1(new BigDecimal(rn1Value));
                        }
                        break;
                    case "SKY":
                        dto.setSky(new BigDecimal(value));
                        break;
                    case "REH":
                        dto.setReh(new BigDecimal(value));
                        break;
                    case "UUU":
                        dto.setUuu(new BigDecimal(value));
                        break;
                    case "VVV":
                        dto.setVvv(new BigDecimal(value));
                        break;
                    case "VEC":
                        dto.setVec(new BigDecimal(value));
                        break;
                    case "WSD":
                        dto.setWsd(new BigDecimal(value));
                        break;
                    case "PTY":
                        dto.setPty(new BigDecimal(value));
                        break;
                    case "LGT":
                        dto.setLgt(new BigDecimal(value));
                        break;
                }
            } catch (NumberFormatException e){
                System.out.println("잘못된 숫자 형식: " + value + " for category " + category);
            }
        }
        return new ArrayList<>(groupedData.values());
    }
}


