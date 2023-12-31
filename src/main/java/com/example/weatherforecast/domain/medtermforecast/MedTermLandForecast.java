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
@Table(name="med_term_land_forecast")
public class MedTermLandForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "reg_id")
    private String regId; // 예보구역코드
    @Column(name = "base_date_time")
    private String baseDateTime; //기준 날짜와 발표시간

    @Column(name = "rnst3_am")
    private Integer rnSt3Am; // 3일 후 오전 강수확률
    @Column(name = "rnst_3_pm")
    private Integer rnSt3Pm; //3일 후 오후 강수확률
    @Column(name = "wf_3_am")
    private String wf3Am; //3일 후 오전날씨예보
    @Column(name = "wf_3_pm")
    private String wf3Pm; //3일 후 오후날씨예보

    @Column(name = "rnst4_am")
    private Integer rnSt4Am;
    @Column(name = "rnst4_pm")
    private Integer rnSt4Pm;
    @Column(name = "wf4_am")
    private String wf4Am;
    @Column(name = "wf4_pm")
    private String wf4Pm;

    @Column(name = "rnst5_am")
    private Integer rnSt5Am;
    @Column(name = "rnst5_pm")
    private Integer rnSt5Pm;
    @Column(name = "wf5_am")
    private String wf5Am;
    @Column(name = "wf5_pm")
    private String wf5Pm;

    @Column(name = "rnst6_am")
    private Integer rnSt6Am;
    @Column(name = "rnst6_pm")
    private Integer rnSt6Pm;
    @Column(name = "wf6_am")
    private String wf6Am;
    @Column(name = "wf6_pm")
    private String wf6Pm;

    @Column(name = "rnst7_am")
    private Integer rnSt7Am;
    @Column(name = "rnst7_pm")
    private Integer rnSt7Pm;
    @Column(name = "wf7_am")
    private String wf7Am;
    @Column(name = "wf7_pm")
    private String wf7Pm;

    @Column(name = "rnst8")
    private Integer rnSt8;
    @Column(name = "wf8")
    private String wf8;

    @Column(name = "rnst9")
    private Integer rnSt9;
    @Column(name = "wf9")
    private String wf9;

    @Column(name = "rnst10")
    private Integer rnSt10;
    @Column(name = "wf10")
    private String wf10;

}
