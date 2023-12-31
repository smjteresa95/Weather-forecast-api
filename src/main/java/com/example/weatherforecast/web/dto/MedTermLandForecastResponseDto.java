package com.example.weatherforecast.web.dto;

import com.example.weatherforecast.domain.medtermforecast.MedTermLandForecast;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedTermLandForecastResponseDto {
    private String regId; // 예보구역코드
    private String baseDateTime;

    private Integer rnSt3Am;
    private Integer rnSt3Pm;
    private String wf3Am;
    private String wf3Pm;

    private Integer rnSt4Am;
    private Integer rnSt4Pm;
    private String wf4Am;
    private String wf4Pm;

    private Integer rnSt5Am;
    private Integer rnSt5Pm;
    private String wf5Am;
    private String wf5Pm;

    private Integer rnSt6Am;
    private Integer rnSt6Pm;
    private String wf6Am;
    private String wf6Pm;

    private Integer rnSt7Am;
    private Integer rnSt7Pm;
    private String wf7Am;
    private String wf7Pm;

    private Integer rnSt8;
    private String wf8;

    private Integer rnSt9;
    private String wf9;

    private Integer rnSt10;
    private String wf10;

    public MedTermLandForecast toEntity() {
        return MedTermLandForecast.builder()
                .regId(this.regId)
                .baseDateTime(this.baseDateTime)
                .rnSt3Am(this.rnSt3Am)
                .rnSt3Pm(this.rnSt3Pm)
                .wf3Am(this.wf3Am)
                .wf3Pm(this.wf3Pm)
                .rnSt4Am(this.rnSt4Am)
                .rnSt4Pm(this.rnSt4Pm)
                .wf4Am(this.wf4Am)
                .wf4Pm(this.wf4Pm)
                .rnSt5Am(this.rnSt5Am)
                .rnSt5Pm(this.rnSt5Pm)
                .wf5Am(this.wf5Am)
                .wf5Pm(this.wf5Pm)
                .rnSt6Am(this.rnSt6Am)
                .rnSt6Pm(this.rnSt6Pm)
                .wf6Am(this.wf6Am)
                .wf6Pm(this.wf6Pm)
                .rnSt7Am(this.rnSt7Am)
                .rnSt7Pm(this.rnSt7Pm)
                .wf7Am(this.wf7Am)
                .wf7Pm(this.wf7Pm)
                .rnSt8(this.rnSt8)
                .wf8(this.wf8)
                .rnSt9(this.rnSt9)
                .wf9(this.wf9)
                .rnSt10(this.rnSt10)
                .wf10(this.wf10)
                .build();
    }

    @Override
    public String toString() {
        return "MedTermLandForecastResponseDto{" +
                "regId='" + regId + '\'' +
                ", baseDateTime='" + baseDateTime + '\'' +
                ", rnSt3Am=" + rnSt3Am +
                ", rnSt3Pm=" + rnSt3Pm +
                ", wf3Am='" + wf3Am + '\'' +
                ", wf3Pm='" + wf3Pm + '\'' +
                ", rnSt4Am=" + rnSt4Am +
                ", rnSt4Pm=" + rnSt4Pm +
                ", wf4Am='" + wf4Am + '\'' +
                ", wf4Pm='" + wf4Pm + '\'' +
                ", rnSt5Am=" + rnSt5Am +
                ", rnSt5Pm=" + rnSt5Pm +
                ", wf5Am='" + wf5Am + '\'' +
                ", wf5Pm='" + wf5Pm + '\'' +
                ", rnSt6Am=" + rnSt6Am +
                ", rnSt6Pm=" + rnSt6Pm +
                ", wf6Am='" + wf6Am + '\'' +
                ", wf6Pm='" + wf6Pm + '\'' +
                ", rnSt7Am=" + rnSt7Am +
                ", rnSt7Pm=" + rnSt7Pm +
                ", wf7Am='" + wf7Am + '\'' +
                ", wf7Pm='" + wf7Pm + '\'' +
                ", rnSt8=" + rnSt8 +
                ", wf8='" + wf8 + '\'' +
                ", rnSt9=" + rnSt9 + '\'' +
                ", wf9='" + wf9 + '\'' +
                ", rnSt10=" + rnSt10 +
                ", wf10='" + wf10 + '\'' +
                '}';
    }
}
