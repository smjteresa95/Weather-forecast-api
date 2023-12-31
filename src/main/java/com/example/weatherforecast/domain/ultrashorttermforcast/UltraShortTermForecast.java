package com.example.weatherforecast.domain.ultrashorttermforcast;

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
@Table(name="ultra_short_term_forecast")
public class UltraShortTermForecast {

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

    @Column(name = "hourly_precipitation", precision = 5, scale = 2)
    private BigDecimal rn1; // 1시간 강수량 (범주 1 mm)
    @Column(name = "sky_condition")
    private BigDecimal sky; // 하늘상태 (코드값)
    @Column(name = "humidity", precision = 5, scale = 2)
    private BigDecimal reh; // 습도
    @Column(name = "east_west_wind_component", precision = 5, scale = 2)
    private BigDecimal uuu; // 동서바람성분
    @Column(name = "north_south_wind_component", precision = 5, scale = 2)
    private BigDecimal vvv; // 남북바람성분
    @Column(name = "wind_direction", precision = 5, scale = 2)
    private BigDecimal vec; // 풍향
    @Column(name = "wind_speed", precision = 5, scale = 2)
    private BigDecimal wsd; // 풍속
    @Column(name = "temperature", precision = 10, scale = 2)
    private BigDecimal t1h; // 기온
    @Column(name = "precipitation_type", length = 4)
    private BigDecimal pty; // 강수형태 코드값
    @Column(name = "lightning", precision = 4)
    private BigDecimal lgt; // 낙뢰
}
