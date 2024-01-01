package com.example.weatherforecast.web.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class ShortTermForecastJsonResponseDto {

    private String level1; //시
    private String level2;  //구
    private String level3; //동/면/읍

    private String baseDate; //기준일
    private String baseTime; //기준시간
    private String fcstDate; //발표일자
    private String fcstTime; //발표시각

    private String pop;   // 강수확률 (%)
    private String pty;   // 강수형태 (코드값)
    private String pcp;  // 1시간 강수량 (범주, 1 mm)
    private BigDecimal reh;   // 습도 (%)
    private BigDecimal sno;  // 1시간 신적설 (범주, 1 cm)
    private String sky;   // 하늘상태 (코드값)
    private BigDecimal tmp;  // 1시간 기온 (℃)

    private BigDecimal tmn;  // 일 최저기온 (℃)
    private BigDecimal tmx;  // 일 최고기온 (℃)
    private BigDecimal uuu;  // 풍속(동서성분) (m/s)
    private BigDecimal vvv;  // 풍속(남북성분) (m/s)
    private BigDecimal wav;  // 파고 (M)
    private BigDecimal vec;  // 풍향 (deg)
    private BigDecimal wsd;  // 풍속 (m/s)

}
