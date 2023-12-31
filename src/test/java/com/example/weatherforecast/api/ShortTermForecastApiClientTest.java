package com.example.weatherforecast.api;

import com.example.weatherforecast.web.dto.ShortTermForecastResponseDto;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:application-api.yml"})
public class ShortTermForecastApiClientTest {

    @Autowired
    private ShortTermForecastApiClient client;

    @Test
    public void parseShortTermForecastJsonDataTest() throws IOException, ParseException {

        String jsonFilePath = "src/test/resources/data/ShortTermForecast.json";
        String jsonData;

        InputStreamReader reader = new InputStreamReader(new FileInputStream(jsonFilePath), StandardCharsets.UTF_8);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(reader);
        jsonData = obj.toJSONString();

        ShortTermForecastResponseDto dto = client.parseShortTermForecastJsonData(jsonData);

        assertNotNull(dto);

        assertEquals(new BigDecimal("0"), dto.getPOP());
        assertEquals(new BigDecimal("0"), dto.getWAV());

    }



}
