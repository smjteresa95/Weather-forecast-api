package com.example.weatherforecast.api;

import com.example.weatherforecast.config.ApiConfig;
import com.example.weatherforecast.web.dto.MedTermLandForecastResponseDto;
import com.example.weatherforecast.web.dto.MedTermTempForecastResponseDto;
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

import java.nio.charset.StandardCharsets;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class MedTermForecastApiClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedTermForecastApiClient.class);

    private final ApiConfig apiConfig;
    private final RestTemplate restTemplate = new RestTemplate();

    //인자로는 지역코드와, 년월일시분을 받는다. 시/분은 0600, 1800 두가지 있다.
    //날짜 받으면 요청인자 만들어 데이터 받기
    //forecastType : land(육상예보), temp(기온예보)
    public String callMedTermForecastApi(String forecastType, String regId, String requestDateTime) {
        String urlStr = getUrlStr(forecastType, regId, requestDateTime);
        LOGGER.info("url: {}", urlStr);

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

    //중기기온예보를 얻어 올지, 중기육상예보를 얻어 올지, forecastType에 따라 정한다.
    private String getUrlStr(String forecastType, String regId, String requestDateTime) {
        String apiUrl = null;
        if(Objects.equals(forecastType, "land")){
            apiUrl = apiConfig.getMedTermLandEndpoint();
        } else if (Objects.equals(forecastType, "temp")){
            apiUrl = apiConfig.getMedTermTempEndpoint();
        }
        String apiKey = apiConfig.getMedTermKey();
        String dataType = apiConfig.getDataType();

        // URL 문자열 구성
        String urlStr = apiUrl + "?serviceKey=" + apiKey + "&dataType=" + dataType;
        urlStr += "&regId=" + regId;
        urlStr += "&tmFc=" + requestDateTime;
        return urlStr;
    }

    //중기기온예보
    //parseMedTermTempForecastJsonData 메서드로 중기육상예보 데이터 객체에 넣어 반환
    public MedTermTempForecastResponseDto getDailyMedTermTempForecastData(String regId, String requestDateTime) throws ParseException {

        String jsonData = callMedTermForecastApi("temp", regId, requestDateTime);

        if (jsonData.isEmpty()){
            LOGGER.error("Med-term temp forecast data not exist");
        }

        MedTermTempForecastResponseDto responseDto = parseMedTermTempForecastJsonData(jsonData);
        responseDto.setBaseDateTime(requestDateTime);

        return responseDto;
    }


    //중기기온예보 데이터 추출
    public MedTermTempForecastResponseDto parseMedTermTempForecastJsonData(String jsonData) throws ParseException {
        if (jsonData == null) {
            LOGGER.error("Received null MedTerm Temp JSON data");
        }

        MedTermTempForecastResponseDto dto = new MedTermTempForecastResponseDto();

        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(jsonData);

        //필요한 데이터 추출
        JSONObject parseResponse = (JSONObject) obj.get("response");
        JSONObject parseBody = (JSONObject) parseResponse.get("body");
        JSONObject parseItems = (JSONObject) parseBody.get("items");
        JSONArray parseItemArray = (JSONArray) parseItems.get("item");

        if (!parseItemArray.isEmpty()) {
            for (Object item : parseItemArray) {
                JSONObject weather = (JSONObject) item;

                //java.lang.ClassCastException: class java.lang.Long cannot be cast to class java.lang.Integer
                dto.setRegId((String) weather.get("regId"));

                dto.setTaMax3(getIntegerValue(weather.get("taMax3")));
                dto.setTaMax3High(getIntegerValue(weather.get("taMax3High")));
                dto.setTaMax3Low(getIntegerValue(weather.get("taMax3Low")));
                dto.setTaMin3(getIntegerValue(weather.get("taMin3")));
                dto.setTaMin3High(getIntegerValue(weather.get("taMin3High")));
                dto.setTaMin3Low(getIntegerValue(weather.get("taMin3Low")));

                dto.setTaMax4(getIntegerValue(weather.get("taMax4")));
                dto.setTaMax4High(getIntegerValue(weather.get("taMax4High")));
                dto.setTaMax4Low(getIntegerValue(weather.get("taMax4Low")));
                dto.setTaMin4(getIntegerValue(weather.get("taMin4")));
                dto.setTaMin4High(getIntegerValue(weather.get("taMin4High")));
                dto.setTaMin4Low(getIntegerValue(weather.get("taMin4Low")));

                dto.setTaMax5(getIntegerValue(weather.get("taMax5")));
                dto.setTaMax5High(getIntegerValue(weather.get("taMax5High")));
                dto.setTaMax5Low(getIntegerValue(weather.get("taMax5Low")));
                dto.setTaMin5(getIntegerValue(weather.get("taMin5")));
                dto.setTaMin5High(getIntegerValue(weather.get("taMin5High")));
                dto.setTaMin5Low(getIntegerValue(weather.get("taMin5Low")));

                dto.setTaMax6(getIntegerValue(weather.get("taMax6")));
                dto.setTaMax6High(getIntegerValue(weather.get("taMax6High")));
                dto.setTaMax6Low(getIntegerValue(weather.get("taMax6Low")));
                dto.setTaMin6(getIntegerValue(weather.get("taMin6")));
                dto.setTaMin6High(getIntegerValue(weather.get("taMin6High")));
                dto.setTaMin6Low(getIntegerValue(weather.get("taMin6Low")));

                dto.setTaMax7(getIntegerValue(weather.get("taMax7")));
                dto.setTaMax7High(getIntegerValue(weather.get("taMax7High")));
                dto.setTaMax7Low(getIntegerValue(weather.get("taMax7Low")));
                dto.setTaMin7(getIntegerValue(weather.get("taMin7")));
                dto.setTaMin7High(getIntegerValue(weather.get("taMin7High")));
                dto.setTaMin7Low(getIntegerValue(weather.get("taMin7Low")));

                dto.setTaMax8(getIntegerValue(weather.get("taMax8")));
                dto.setTaMax8High(getIntegerValue(weather.get("taMax8High")));
                dto.setTaMax8Low(getIntegerValue(weather.get("taMax8Low")));
                dto.setTaMin8(getIntegerValue(weather.get("taMin8")));
                dto.setTaMin8High(getIntegerValue(weather.get("taMin8High")));
                dto.setTaMin8Low(getIntegerValue(weather.get("taMin8Low")));

                dto.setTaMax9(getIntegerValue(weather.get("taMax9")));
                dto.setTaMax9High(getIntegerValue(weather.get("taMax9High")));
                dto.setTaMax9Low(getIntegerValue(weather.get("taMax9Low")));
                dto.setTaMin9(getIntegerValue(weather.get("taMin9")));
                dto.setTaMin9High(getIntegerValue(weather.get("taMin9High")));
                dto.setTaMin9Low(getIntegerValue(weather.get("taMin9Low")));

                dto.setTaMax10(getIntegerValue(weather.get("taMax10")));
                dto.setTaMax10High(getIntegerValue(weather.get("taMax10High")));
                dto.setTaMax10Low(getIntegerValue(weather.get("taMax10Low")));
                dto.setTaMin10(getIntegerValue(weather.get("taMin10")));
                dto.setTaMin10High(getIntegerValue(weather.get("taMin10High")));
                dto.setTaMin10Low(getIntegerValue(weather.get("taMin10Low")));
            }
        }
        LOGGER.info("parsed med-term temp data: {}", dto);
        return dto;
    }


    //중기육상예보

    //parseMedTermLandForecastJsonData 메서드로 중기육상예보 데이터 객체에 넣어 반환
    public MedTermLandForecastResponseDto getDailyMedTermLandForecastData(String regId, String requestDateTime) throws ParseException {

        String jsonData = callMedTermForecastApi("land", regId, requestDateTime);

        if (jsonData.isEmpty()){
            LOGGER.error("Med-term forecast data not exist");
        }

        MedTermLandForecastResponseDto responseDto = parseMedTermLandForecastJsonData(jsonData, requestDateTime);
        responseDto.setBaseDateTime(requestDateTime);

        return responseDto;
    }


    //중기육상예보 데이터 추출
    public MedTermLandForecastResponseDto parseMedTermLandForecastJsonData(String jsonData, String requestDateTime) throws ParseException {
        if (jsonData == null) {
            LOGGER.error("Received null MedTerm Land JSON data");
        }

        MedTermLandForecastResponseDto dto = new MedTermLandForecastResponseDto();

        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(jsonData);

        //필요한 데이터 추출
        JSONObject parseResponse = (JSONObject) obj.get("response");
        JSONObject parseBody = (JSONObject) parseResponse.get("body");
        JSONObject parseItems = (JSONObject) parseBody.get("items");
        JSONArray parseItemArray = (JSONArray) parseItems.get("item");

        if (!parseItemArray.isEmpty()) {
            for (Object item : parseItemArray) {
                JSONObject weather = (JSONObject) item;

                //java.lang.ClassCastException: class java.lang.Long cannot be cast to class java.lang.Integer
                dto.setRegId((String) weather.get("regId"));
                dto.setBaseDateTime((String) weather.get(requestDateTime));

                dto.setRnSt3Am(getIntegerValue(weather.get("rnSt3Am")));
                dto.setRnSt3Pm(getIntegerValue(weather.get("rnSt3Pm")));
                dto.setWf3Am((String) weather.get("wf3Am"));
                dto.setWf3Pm((String) weather.get("wf3Pm"));

                dto.setRnSt4Am(getIntegerValue(weather.get("rnSt4Am")));
                dto.setRnSt4Pm(getIntegerValue(weather.get("rnSt4Pm")));
                dto.setWf4Am((String) weather.get("wf4Am"));
                dto.setWf4Pm((String) weather.get("wf4Pm"));

                dto.setRnSt5Am(getIntegerValue(weather.get("rnSt5Am")));
                dto.setRnSt5Pm(getIntegerValue(weather.get("rnSt5Pm")));
                dto.setWf5Am((String) weather.get("wf5Am"));
                dto.setWf5Pm((String) weather.get("wf5Pm"));

                dto.setRnSt6Am(getIntegerValue(weather.get("rnSt6Am")));
                dto.setRnSt6Pm(getIntegerValue(weather.get("rnSt6Pm")));
                dto.setWf6Am((String) weather.get("wf6Am"));
                dto.setWf6Pm((String) weather.get("wf6Pm"));

                dto.setRnSt7Am(getIntegerValue(weather.get("rnSt7Am")));
                dto.setRnSt7Pm(getIntegerValue(weather.get("rnSt7Pm")));
                dto.setWf7Am((String) weather.get("wf7Am"));
                dto.setWf7Pm((String) weather.get("wf7Pm"));

                dto.setRnSt8(getIntegerValue(weather.get("rnSt8")));
                dto.setWf8((String) weather.get("wf8"));

                dto.setRnSt9(getIntegerValue(weather.get("rnSt9")));
                dto.setWf9((String) weather.get("wf9"));

                dto.setRnSt10(getIntegerValue(weather.get("rnSt10")));
                dto.setWf10((String) weather.get("wf10"));
            }
        }
        LOGGER.info("parsed med-term land data: {}", dto);
        return dto;
    }

    private Integer getIntegerValue(Object value) {
        if (value instanceof Long) {
            return ((Long) value).intValue();
        } else if (value instanceof Integer) {
            return (Integer) value;
        } else {
            return null;
        }
    }

}