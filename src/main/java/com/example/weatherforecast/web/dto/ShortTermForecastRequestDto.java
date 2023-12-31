package com.example.weatherforecast.web.dto;

import lombok.*;

//단기예보 요정인자
@Getter @Setter
@AllArgsConstructor @Builder
@RequiredArgsConstructor
public class ShortTermForecastRequestDto {
    private String regionCode;
    private String baseDate;
    private String baseTime;
}
