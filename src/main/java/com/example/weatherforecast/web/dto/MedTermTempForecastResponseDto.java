package com.example.weatherforecast.web.dto;

import com.example.weatherforecast.domain.medtermforecast.MedTermTempForecast;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedTermTempForecastResponseDto {
    private String regId; // 예보구역코드
    private String baseDateTime;

    private Integer taMax3;
    private Integer taMax3High;
    private Integer taMax3Low;
    private Integer taMin3;
    private Integer taMin3High;
    private Integer taMin3Low;

    private Integer taMax4;
    private Integer taMax4High;
    private Integer taMax4Low;
    private Integer taMin4;
    private Integer taMin4High;
    private Integer taMin4Low;

    private Integer taMax5;
    private Integer taMax5High;
    private Integer taMax5Low;
    private Integer taMin5;
    private Integer taMin5High;
    private Integer taMin5Low;

    private Integer taMax6;
    private Integer taMax6High;
    private Integer taMax6Low;
    private Integer taMin6;
    private Integer taMin6High;
    private Integer taMin6Low;

    private Integer taMax7;
    private Integer taMax7High;
    private Integer taMax7Low;
    private Integer taMin7;
    private Integer taMin7High;
    private Integer taMin7Low;

    private Integer taMax8;
    private Integer taMax8High;
    private Integer taMax8Low;
    private Integer taMin8;
    private Integer taMin8High;
    private Integer taMin8Low;

    private Integer taMax9;
    private Integer taMax9High;
    private Integer taMax9Low;
    private Integer taMin9;
    private Integer taMin9High;
    private Integer taMin9Low;

    private Integer taMax10;
    private Integer taMax10High;
    private Integer taMax10Low;
    private Integer taMin10;
    private Integer taMin10High;
    private Integer taMin10Low;

    public MedTermTempForecast toEntity() {
        return MedTermTempForecast.builder()
                .regId(regId)
                .baseDateTime(baseDateTime)
                .taMax3(taMax3)
                .taMax3High(taMax3High)
                .taMax3Low(taMax3Low)
                .taMin3(taMin3)
                .taMin3High(taMin3High)
                .taMin3Low(taMin3Low)
                .taMax4(taMax4)
                .taMax4High(taMax4High)
                .taMax4Low(taMax4Low)
                .taMin4(taMin4)
                .taMin4High(taMin4High)
                .taMin4Low(taMin4Low)
                .taMax5(taMax5)
                .taMax5High(taMax5High)
                .taMax5Low(taMax5Low)
                .taMin5(taMin5)
                .taMin5High(taMin5High)
                .taMin5Low(taMin5Low)
                .taMax6(taMax6)
                .taMax6High(taMax6High)
                .taMax6Low(taMax6Low)
                .taMin6(taMin6)
                .taMin6High(taMin6High)
                .taMin6Low(taMin6Low)
                .taMax7(taMax7)
                .taMax7High(taMax7High)
                .taMax7Low(taMax7Low)
                .taMin7(taMin7)
                .taMin7High(taMin7High)
                .taMin7Low(taMin7Low)
                .taMax8(taMax8)
                .taMax8High(taMax8High)
                .taMax8Low(taMax8Low)
                .taMin8(taMin8)
                .taMin8High(taMin8High)
                .taMin8Low(taMin8Low)
                .taMax9(taMax9)
                .taMax9High(taMax9High)
                .taMax9Low(taMax9Low)
                .taMin9(taMin9)
                .taMin9High(taMin9High)
                .taMin9Low(taMin9Low)
                .taMax10(taMax10)
                .taMax10High(taMax10High)
                .taMax10Low(taMax10Low)
                .taMin10(taMin10)
                .taMin10High(taMin10High)
                .taMin10Low(taMin10Low)
                .build();
    }


}
