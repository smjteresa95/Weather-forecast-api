package com.example.weatherforecast.web.controller;

import com.example.weatherforecast.service.ShortTermForecastService;
import com.example.weatherforecast.service.UltraShortTermForecastService;
import com.example.weatherforecast.web.dto.ShortTermForecastRequestDto;
import com.example.weatherforecast.web.dto.UltraShortTermForecastRequestDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/weather-forecast/")
@RequiredArgsConstructor
public class ShortTermForecastApiController {

    private final ShortTermForecastService service;
    private final UltraShortTermForecastService ultraService;

    //OPEN API 에서 받아온 데이터 값을 DB에 저장
    //인자로 regionCode, baseDate, baseTime 받는다.
    @PostMapping("/short-term")
    public ResponseEntity<?> saveShortTermForecast(@ModelAttribute ShortTermForecastRequestDto request) {
        service.saveShortTermForecastData(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //DB에 저장 된 값을 받아 JSON 형식으로 반환
    @GetMapping("/short-term/details")
    public ResponseEntity<String> getShortTermForecastDetail(@ModelAttribute ShortTermForecastRequestDto request) {

        try {
            String response = service.getShortTermForecastDescription(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("API를 불러오는데 실패했습니다.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //인자로 baseDate, regionCode 받는다.
    @PostMapping("/ultra-short-term")
    public ResponseEntity<?> saveUltraShortTermForecastData(@ModelAttribute UltraShortTermForecastRequestDto request){
        try {
            ultraService.saveDailyForecastDataList(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //인자로 baseDate, regionCode, fcstTime 받는다.
    @GetMapping("/ultra-short-term/details")
    public ResponseEntity<String> getUltraShortTermForecastDetail(@ModelAttribute UltraShortTermForecastRequestDto requestDto,
                                                                  @RequestParam("fcstTime") String fcstTime) {
        String response = ultraService.generateJsonDataForGivenFcstTime(requestDto, fcstTime);
        try {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            throw new RuntimeException("JSON 데이터를 얻어 올 수 없습니다.");
        }
    }
}
