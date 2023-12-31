package com.example.weatherforecast.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class MedTermForecastRequestDto {
    String regId;
    Integer dayNumber; //몇일 후의 예보를 받아 올 지 null값인 경우 전체 예보를 가져온다.
}
