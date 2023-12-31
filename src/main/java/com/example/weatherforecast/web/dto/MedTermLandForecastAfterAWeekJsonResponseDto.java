package com.example.weatherforecast.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedTermLandForecastAfterAWeekJsonResponseDto {
    private String regId; // 예보구역코드
    private String baseDate;
    private Integer rnSt;
    private String wf;

    @Override
    public String toString() {
        return "MedTermLandForecastWithinAWeekJsonResponseDto{" +
                "regId='" + regId + '\'' +
                ", baseDate='" + baseDate + '\'' +
                ", rnStAm=" + rnSt +
                ", wfPm='" + wf + '\'' +
                '}';
    }
}
