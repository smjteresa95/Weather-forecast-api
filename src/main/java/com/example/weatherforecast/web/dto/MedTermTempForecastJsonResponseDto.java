package com.example.weatherforecast.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedTermTempForecastJsonResponseDto {
    private String regId;
    private String baseDateTime;

    private Integer taMax;
    private Integer taMaxHigh;
    private Integer taMaxLow;
    private Integer taMin;
    private Integer taMinHigh;
    private Integer taMinLow;
}
