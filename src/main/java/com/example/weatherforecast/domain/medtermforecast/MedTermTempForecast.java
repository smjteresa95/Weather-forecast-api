package com.example.weatherforecast.domain.medtermforecast;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="med_term_temp_forecast")
public class MedTermTempForecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "reg_id")
    private String regId; // 예보구역코드
    @Column(name = "base_date_time")
    private String baseDateTime; //기준 날짜와 예보팔표시간

    @Column(name = "ta_max3")
    private Integer taMax3; //3일 후 예상최저기온(℃)
    @Column(name = "ta_max3_high")
    private Integer taMax3High; //3일 후 예상최저기온 하한 범위
    @Column(name = "ta_max3_low")
    private Integer taMax3Low; //3일 후 예상최저기온 상한 범위
    @Column(name = "ta_min3")
    private Integer taMin3; //3일 후 예상최고기온(℃)
    @Column(name = "ta_min3_high")
    private Integer taMin3High; //3일 후 예상최고기온 하한 범위
    @Column(name = "ta_min3_low")
    private Integer taMin3Low; //3일 선택일 후 예상최고기온 상한 범위

    @Column(name = "ta_max4")
    private Integer taMax4;
    @Column(name = "ta_max4_high")
    private Integer taMax4High;
    @Column(name = "ta_max4_low")
    private Integer taMax4Low;
    @Column(name = "ta_min4")
    private Integer taMin4;
    @Column(name = "ta_min4_high")
    private Integer taMin4High;
    @Column(name = "ta_min4_low")
    private Integer taMin4Low;

    @Column(name = "ta_max5")
    private Integer taMax5;
    @Column(name = "ta_max5_high")
    private Integer taMax5High;
    @Column(name = "ta_max5_low")
    private Integer taMax5Low;
    @Column(name = "ta_min5")
    private Integer taMin5;
    @Column(name = "ta_min5_high")
    private Integer taMin5High;
    @Column(name = "ta_min5_low")
    private Integer taMin5Low;

    @Column(name = "ta_max6")
    private Integer taMax6;
    @Column(name = "ta_max6_high")
    private Integer taMax6High;
    @Column(name = "ta_max6_low")
    private Integer taMax6Low;
    @Column(name = "ta_min6")
    private Integer taMin6;
    @Column(name = "ta_min6_high")
    private Integer taMin6High;
    @Column(name = "ta_min6_low")
    private Integer taMin6Low;

    @Column(name = "ta_max7")
    private Integer taMax7;
    @Column(name = "ta_max7_high")
    private Integer taMax7High;
    @Column(name = "ta_max7_low")
    private Integer taMax7Low;
    @Column(name = "ta_min7")
    private Integer taMin7;
    @Column(name = "ta_min7_high")
    private Integer taMin7High;
    @Column(name = "ta_min7_low")
    private Integer taMin7Low;

    @Column(name = "ta_max8")
    private Integer taMax8;
    @Column(name = "ta_max8_high")
    private Integer taMax8High;
    @Column(name = "ta_max8_low")
    private Integer taMax8Low;
    @Column(name = "ta_min8")
    private Integer taMin8;
    @Column(name = "ta_min8_high")
    private Integer taMin8High;
    @Column(name = "ta_min8_low")
    private Integer taMin8Low;

    @Column(name = "ta_max9")
    private Integer taMax9;
    @Column(name = "ta_max9_high")
    private Integer taMax9High;
    @Column(name = "ta_max9_low")
    private Integer taMax9Low;
    @Column(name = "ta_min9")
    private Integer taMin9;
    @Column(name = "ta_min9_high")
    private Integer taMin9High;
    @Column(name = "ta_min9_low")
    private Integer taMin9Low;

    @Column(name = "ta_max10")
    private Integer taMax10;
    @Column(name = "ta_max10_high")
    private Integer taMax10High;
    @Column(name = "ta_max10_low")
    private Integer taMax10Low;
    @Column(name = "ta_min10")
    private Integer taMin10;
    @Column(name = "ta_min10_high")
    private Integer taMin10High;
    @Column(name = "ta_min10_low")
    private Integer taMin10Low;



}
