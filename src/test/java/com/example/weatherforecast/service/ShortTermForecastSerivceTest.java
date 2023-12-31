package com.example.weatherforecast.service;

import com.example.weatherforecast.api.ShortTermForecastApiClient;
import com.example.weatherforecast.domain.shorttermforecast.ShortTermForecast;
import com.example.weatherforecast.domain.shorttermforecast.ShortTermForecastRepository;
import com.example.weatherforecast.util.mapper.Mapper;
import com.example.weatherforecast.web.dto.ShortTermForecastRequestDto;
import com.example.weatherforecast.web.dto.ShortTermForecastResponseDto;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ShortTermForecastSerivceTest {

    @Autowired
    RegionDataService regionDataService;

    @Autowired
    ShortTermForecastRepository repository;

    @Autowired
    ShortTermForecastService service;

    @Autowired
    Mapper mapper;

    @Autowired
    private ShortTermForecastApiClient client;

    @Before
    public void setUp(){
        regionDataService.loadAndSaveShortTermRegionData();
    }

    @Test
    @DisplayName("JSON 데이터를 받아서 각 코드에 해당 하는 값을 DB에 저장")
    public void saveShortTermForecastDataTest() throws IOException, ParseException {

        String jsonFilePath = "src/test/resources/data/ShortTermForecast.json";
        String jsonData;

        InputStreamReader reader = new InputStreamReader(new FileInputStream(jsonFilePath), StandardCharsets.UTF_8);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(reader);
        jsonData = obj.toJSONString();

        ShortTermForecastResponseDto dto = client.parseShortTermForecastJsonData(jsonData);

        ShortTermForecastRequestDto requestDto = ShortTermForecastRequestDto.builder()
                .regionCode("2826051500")
                .baseDate("20231223")
                .baseTime("0800")
                .build();

        Optional<ShortTermForecast> optionalEntity = repository.findByMatchingCriteria(
                requestDto.getRegionCode(),
                requestDto.getBaseDate(),
                requestDto.getBaseTime());

//        if (optionalEntity.isPresent()) {
//            // Optional이 값이 있는 경우
//            ShortTermForecastResponseDto responseDto = mapper.ShortTermForecastToDto(optionalEntity.get());
//            System.out.println((responseDto.getPTY()).toString());
//        } else {
//            System.out.println("값이 없어요");
//        }

        //when
        service.saveShortTermForecastData(requestDto);

        //then
        assertThat(repository.findById(1L).get().getBaseDate())
                .isEqualTo(dto.getBaseDate());
    }

//    @Test
//    @DisplayName("DB에 저장된 단기예보 데이터를 JSON형식으로 받는지 확인")
//    public void fetchMatchingShortTermForecastDataTest() throws IOException, ParseException {
//        ShortTermForecastRequestDto requestDto = ShortTermForecastRequestDto.builder()
//                .regionCode("2826051500")
//                .baseDate("20231223")
//                .baseTime("0800")
//                .build();
//
//        String jsonFilePath = "src/test/resources/data/ShortTermForecast.json";
//        String jsonData;
//
//        InputStreamReader reader = new InputStreamReader(new FileInputStream(jsonFilePath), StandardCharsets.UTF_8);
//        JSONParser parser = new JSONParser();
//        JSONObject obj = (JSONObject) parser.parse(reader);
//        jsonData = obj.toJSONString();
//
//        ShortTermForecastResponseDto expectedDto = client.parseShortTermForecastJsonData(jsonData);
//
//        //when
//        Optional<String> result = service.fetchMatchingShortTermForecastData(requestDto);
//        ShortTermForecastResponseDto resultDto = client.parseShortTermForecastJsonData(result.get());
//
//        //then
//        assertThat(expectedDto.getPOP()).isEqualTo(resultDto.getPOP());
//    }
}
