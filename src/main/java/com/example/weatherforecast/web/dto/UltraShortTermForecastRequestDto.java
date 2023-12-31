package com.example.weatherforecast.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class UltraShortTermForecastRequestDto {
    private String regionCode;
    private String baseDate;
}
