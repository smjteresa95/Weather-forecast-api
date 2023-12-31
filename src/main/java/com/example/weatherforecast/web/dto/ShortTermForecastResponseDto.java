package com.example.weatherforecast.web.dto;

import com.example.weatherforecast.domain.shorttermforecast.ShortTermForecast;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class ShortTermForecastResponseDto {
    private String regionCode; //행정구역코드
    private String baseDate; //발표일자
    private String baseTime; //발표시각
    private String fcstDate; //발표일자
    private String fcstTime; //발표시각
    private BigDecimal POP;   // 강수확률 (%)
    private BigDecimal PTY;   // 강수형태 (코드값)
    private BigDecimal PCP;  // 1시간 강수량 (범주, 1 mm)
    private BigDecimal REH;   // 습도 (%)
    private BigDecimal SNO;  // 1시간 신적설 (범주, 1 cm)
    private BigDecimal SKY;   // 하늘상태 (코드값)
    private BigDecimal TMP;  // 1시간 기온 (℃)
    private BigDecimal TMN;  // 일 최저기온 (℃)
    private BigDecimal TMX;  // 일 최고기온 (℃)
    private BigDecimal UUU;  // 풍속(동서성분) (m/s)
    private BigDecimal VVV;  // 풍속(남북성분) (m/s)
    private BigDecimal WAV;  // 파고 (M)
    private BigDecimal VEC;  // 풍향 (deg)
    private BigDecimal WSD;  // 풍속 (m/s)

    public ShortTermForecast toEntity() {
        return ShortTermForecast.builder()
                .regionCode(this.getRegionCode())
                .baseDate(this.getBaseDate())
                .baseTime(this.getBaseTime())
                .fcstDate(this.getFcstDate())
                .fcstTime(this.getFcstTime())
                .pop(this.getPOP())
                .pty(this.getPTY())
                .pcp(this.getPCP())
                .reh(this.getREH())
                .sno(this.getSNO())
                .sky(this.getSKY())
                .tmp(this.getTMP())
                .tmn(this.getTMN())
                .tmx(this.getTMX())
                .uuu(this.getUUU())
                .vvv(this.getVVV())
                .wav(this.getWAV())
                .vec(this.getVEC())
                .wsd(this.getWSD())
                .build();
    }
}
