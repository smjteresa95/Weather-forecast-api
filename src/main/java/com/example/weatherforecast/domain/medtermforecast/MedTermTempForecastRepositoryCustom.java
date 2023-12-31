package com.example.weatherforecast.domain.medtermforecast;

import com.example.weatherforecast.web.dto.MedTermForecastRequestDto;
import com.example.weatherforecast.web.dto.MedTermTempForecastJsonResponseDto;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedTermTempForecastRepositoryCustom {
    Optional<MedTermTempForecastJsonResponseDto> findDynamicTempForecastByRegIdAndBaseDateTime(MedTermForecastRequestDto requestDto, String baseDate);

}
