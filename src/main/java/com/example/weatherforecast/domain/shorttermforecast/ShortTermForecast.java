package com.example.weatherforecast.domain.shorttermforecast;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor @Builder
@Entity
@Table(name="short_term_forecast")
public class ShortTermForecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "region_code")
    private String regionCode; //행정구역코드

    @Column(name = "base_date")
    private String baseDate; //발표일자

    @Column(name = "base_time")
    private String baseTime; //발표시각

    @Column(name = "fcst_date")
    private String fcstDate; //발표일자

    @Column(name = "fcst_time")
    private String fcstTime; //발표시각


    @Column(name = "precipitation_probability")
    private String pop; // 강수확률 (%)

    @Column(name = "precipitation_type")
    private String pty; // 강수형태 (코드값)

    @Column(name = "hourly_precipitation")
    private String pcp; // 1시간 강수량 (범주 1 mm)

    @Column(name = "humidity", precision = 5, scale = 2)
    private BigDecimal reh; // 습도

    @Column(name = "hourly_new_snow", precision = 5, scale = 2)
    private BigDecimal sno; // 1시간 신적설 (범주 1 cm)

    @Column(name = "sky_condition")
    private String sky; // 하늘상태 (코드값)

    @Column(name = "hourly_temperature", precision = 5, scale = 2)
    private BigDecimal tmp; // 1시간 기온 (℃)


    @Column(name = "min_temperature", precision = 5, scale = 2)
    private BigDecimal tmn; // 일 최저기온 (℃)

    @Column(name = "max_temperature", precision = 5, scale = 2)
    private BigDecimal tmx; // 일 최고기온 (℃)

    @Column(name = "east_west_wind_component", precision = 5, scale = 2)
    private BigDecimal uuu; // 동서바람성분

    @Column(name = "north_south_wind_component", precision = 5, scale = 2)
    private BigDecimal vvv; // 남북바람성분

    @Column(name = "wave_height", precision = 5, scale = 2)
    private BigDecimal wav; // 파고 (M)

    @Column(name = "wind_direction", precision = 5, scale = 2)
    private BigDecimal vec; // 풍향

    @Column(name = "wind_speed", precision = 5, scale = 2)
    private BigDecimal wsd; // 풍속

}
