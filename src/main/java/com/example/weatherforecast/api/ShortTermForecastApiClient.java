package com.example.weatherforecast.api;

import com.example.weatherforecast.config.ApiConfig;
import com.example.weatherforecast.domain.region.ShortTermRegionRepository;
import com.example.weatherforecast.util.mapper.Mapper;
import com.example.weatherforecast.web.dto.ShortTermForecastRequestDto;
import com.example.weatherforecast.web.dto.ShortTermForecastResponseDto;
import com.example.weatherforecast.web.dto.ShortTermRegionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class ShortTermForecastApiClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShortTermForecastApiClient.class);


    private final ApiConfig apiConfig;
    private final Mapper mapper;
    private final RestTemplate restTemplate = new RestTemplate();

    private final ShortTermRegionRepository regionRepository;

    //JSON 데이터를 가지고 오는 메서드
    public String callShortTermForecastApi(ShortTermForecastRequestDto request) throws IOException {

        //nx, ny를 행정구역코드에서 얻어오기
        ShortTermRegionDto regionDto = mapper.ShortTermRegionToDto(regionRepository.findRegionByCode(request.getRegionCode()).get());
        String nx = String.valueOf(regionDto.getGridX());
        String ny = String.valueOf(regionDto.getGridY());

        String apiUrl = apiConfig.getShortTermEndpoint();

        String apiKey = apiConfig.getShortTermKey();
        String dataType = apiConfig.getDataType();

        // URL 문자열 구성
        String urlStr = apiUrl + "?serviceKey=" + apiKey + "&dataType=" + dataType;
        urlStr += "&base_date=" + request.getBaseDate();
        urlStr += "&base_time=" + request.getBaseTime();
//        urlStr += "&numOfRows=" + request.getNumOfRows();
//        urlStr += "&pageNo=" + request.getPageNo();
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
            return response.getBody();
        } else {
            return "API 호출 실패: " + response.getStatusCode();
        }
    }

    //JSON 데이터를 파싱하는 메서드
    public ShortTermForecastResponseDto parseShortTermForecastJsonData(String jsonData) throws JsonProcessingException, ParseException {

        if (jsonData == null) {
            LOGGER.error("Received null ShortTerm JSON data");
        }
        ShortTermForecastResponseDto dto = new ShortTermForecastResponseDto();

        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(jsonData);

        //필요한 데이터 추출
        JSONObject parseResponse = (JSONObject) obj.get("response");
        JSONObject parseBody = (JSONObject) parseResponse.get("body");
        JSONObject parseItems = (JSONObject) parseBody.get("items");
        JSONArray parseItem = (JSONArray) parseItems.get("item");

        for (Object itemObj : parseItem) {
            JSONObject weather = (JSONObject) itemObj;
            String category = (String) weather.get("category");
            String value = (String) weather.get("fcstValue");

            String baseDate = (String) weather.get("baseDate");
            String baseTime = (String) weather.get("baseTime");
            String fcstDate = (String) weather.get("fcstDate");
            String fcstTime = (String) weather.get("fcstTime");

            dto.setBaseDate(baseDate);
            dto.setBaseTime(baseTime);
            dto.setFcstDate(fcstDate);
            dto.setFcstTime(fcstTime);

            // 필드에 데이터 설정
            switch (category) {
                case "POP":
                    dto.setPOP(value);
                    break;
                case "PTY":
                    dto.setPTY(value);
                    break;
                case "PCP":
                    String rn1Value = value.replace("mm", "").trim();
                    if (rn1Value.isEmpty() || rn1Value.equals("강수없음")) {
                        dto.setPCP("0");
                    } else {
                        dto.setPCP(rn1Value);
                    }
                    break;
                case "REH":
                    dto.setREH(new BigDecimal(value));
                    break;
                case "SNO":
                    dto.setSNO(new BigDecimal(value));
                    break;
                case "SKY":
                    dto.setSKY(value);
                    break;
                case "TMP":
                    dto.setTMP(new BigDecimal(value));
                    break;
                case "TMN":
                    dto.setTMN(new BigDecimal(value));
                    break;
                case "TMX":
                    dto.setTMX(new BigDecimal(value));
                    break;
                case "UUU":
                    dto.setUUU(new BigDecimal(value));
                    break;
                case "VVV":
                    dto.setVVV(new BigDecimal(value));
                    break;
                case "WAV":
                    dto.setWAV(new BigDecimal(value));
                    break;
                case "VEC":
                    dto.setVEC(new BigDecimal(value));
                    break;
                case "WSD":
                    dto.setWSD(new BigDecimal(value));
                    break;
            }
        }
        return dto;
    }
}

