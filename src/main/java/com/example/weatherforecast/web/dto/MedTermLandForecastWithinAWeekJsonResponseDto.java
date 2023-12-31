package com.example.weatherforecast.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedTermLandForecastWithinAWeekJsonResponseDto {
    private String regId; // 예보구역코드
    private String baseDate;
    private Integer rnStAm;
    private Integer rnStPm;
    private String wfAm;
    private String wfPm;

    @Override
    public String toString() {
        return "MedTermLandForecastWithinAWeekJsonResponseDto{" +
                "regId='" + regId + '\'' +
                ", baseDate='" + baseDate + '\'' +
                ", rnStAm=" + rnStAm +
                ", rnStPm=" + rnStPm +
                ", wfAm='" + wfAm + '\'' +
                ", wfPm='" + wfPm + '\'' +
                '}';
    }


}
