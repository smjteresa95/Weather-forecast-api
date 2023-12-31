package com.example.weatherforecast.web.controller;

import com.example.weatherforecast.service.MedTermLandForecastService;
import com.example.weatherforecast.service.MedTermTempForecastService;
import com.example.weatherforecast.web.dto.MedTermForecastRequestDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather-forecast/med-term")
@RequiredArgsConstructor
public class MedTermForecastApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedTermForecastApiController.class);

    private final MedTermLandForecastService landService;
    private final MedTermTempForecastService tempService;

    //OPEN API에서 받아온 데이터 값을 DB에 저장
    @PostMapping("/land")
    public ResponseEntity<?> saveMedTermLandForecast(@RequestParam("regId") String regId) throws ParseException {
        landService.saveDailyMedTermForecastDataList(regId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    DB에 저장된 값 에서 dayNumber 일 후의 날씨 JSON 형으로 얻어 오기, 인자는 regId 와 dayNumber
    @GetMapping("/land/details")
    public ResponseEntity<String> getMedTermForest(@ModelAttribute MedTermForecastRequestDto requestDto){
        String response = landService.getMedTermForecastAfterDayNumber(requestDto);
        LOGGER.info("extracted data: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/temp")
    public ResponseEntity<?> saveMedTermTempForecast(@RequestParam("regId") String regId) throws ParseException {
        tempService.saveDailyMedTermTempForecastDataList(regId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/temp/details")
    public ResponseEntity<String> getMedTermTempForecast(@ModelAttribute MedTermForecastRequestDto requestDto){
        String response = tempService.getMedTermTempForecastByDayNumber(requestDto);
        LOGGER.info("extracted data: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
