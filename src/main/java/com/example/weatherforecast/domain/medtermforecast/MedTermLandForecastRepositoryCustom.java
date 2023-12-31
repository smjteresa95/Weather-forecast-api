package com.example.weatherforecast.domain.medtermforecast;

import com.example.weatherforecast.web.dto.MedTermForecastRequestDto;
import com.example.weatherforecast.web.dto.MedTermLandForecastAfterAWeekJsonResponseDto;
import com.example.weatherforecast.web.dto.MedTermLandForecastWithinAWeekJsonResponseDto;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedTermLandForecastRepositoryCustom {

    //dayNumber 값에 따라, 7일 이전의 예보를 가지고 온다.
    Optional<MedTermLandForecastWithinAWeekJsonResponseDto> findDynamicForecastWithinAWeekByRegIdAndBaseDate(MedTermForecastRequestDto requestDto, String baseDate);
    //dayNumber 값에 따라, 7일 이후의 예보를 가지고 온다.
    Optional<MedTermLandForecastAfterAWeekJsonResponseDto> findDynamicForecastAfterAWeekByRegIdAndBaseDate(MedTermForecastRequestDto requestDto, String baseDate);



}

