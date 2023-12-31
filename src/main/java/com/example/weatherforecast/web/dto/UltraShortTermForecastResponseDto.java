package com.example.weatherforecast.web.dto;


import com.example.weatherforecast.domain.ultrashorttermforcast.UltraShortTermForecast;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class UltraShortTermForecastResponseDto {
    private String regionCode; // 행정구역코드
    private String baseDate; // 발표일자
    private String baseTime; // 발표시각
    private String fcstDate; // 예보일자
    private String fcstTime; // 예보시각
    private BigDecimal rn1; // 1시간 강수량 (범주 1 mm)
    private BigDecimal sky; // 하늘상태 (코드값)
    private BigDecimal reh; // 습도
    private BigDecimal uuu; // 동서바람성분
    private BigDecimal vvv; // 남북바람성분
    private BigDecimal vec; // 풍향
    private BigDecimal wsd; // 풍속
    private BigDecimal t1h; // 기온
    private BigDecimal pty; // 강수형태 코드값
    private BigDecimal lgt; // 낙뢰

    public UltraShortTermForecast toEntity() {
        return UltraShortTermForecast.builder()
                .regionCode(this.regionCode)
                .baseDate(this.baseDate)
                .baseTime(this.baseTime)
                .fcstDate(this.fcstDate)
                .fcstTime(this.fcstTime)
                .rn1(this.rn1)
                .sky(this.sky)
                .reh(this.reh)
                .uuu(this.uuu)
                .vvv(this.vvv)
                .vec(this.vec)
                .wsd(this.wsd)
                .t1h(this.t1h)
                .pty(this.pty)
                .lgt(this.lgt)
                .build();
    }
}
