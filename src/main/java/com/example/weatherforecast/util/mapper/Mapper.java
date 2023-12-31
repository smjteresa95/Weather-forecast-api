package com.example.weatherforecast.util.mapper;

import com.example.weatherforecast.domain.medtermforecast.MedTermLandForecast;
import com.example.weatherforecast.domain.medtermforecast.MedTermTempForecast;
import com.example.weatherforecast.domain.region.MedTermLandRegion;
import com.example.weatherforecast.domain.region.ShortTermRegion;
import com.example.weatherforecast.domain.shorttermforecast.ShortTermForecast;
import com.example.weatherforecast.domain.ultrashorttermforcast.UltraShortTermForecast;
import com.example.weatherforecast.web.dto.*;
import org.springframework.stereotype.Component;

//entity에서 dto로 바꾸는 메서드의 집합
@Component
public class Mapper {

    public ShortTermRegionDto ShortTermRegionToDto(ShortTermRegion entity){
        return ShortTermRegionDto.builder()
                .regionCode(entity.getRegionCode())
                .level1(entity.getLevel1())
                .level2(entity.getLevel2())
                .level3(entity.getLevel3())
                .gridX(entity.getGridX())
                .gridY(entity.getGridY())
                .longitudeDegrees(entity.getLongitudeDegrees())
                .longitudeMinutes(entity.getLongitudeMinutes())
                .longitudeSeconds(entity.getLongitudeSeconds())
                .latitudeDegrees(entity.getLatitudeDegrees())
                .latitudeMinutes(entity.getLatitudeMinutes())
                .latitudeSeconds(entity.getLatitudeSeconds())
                .build();
    }

    MedTermRegionDto medTermLandRegionToDto(MedTermLandRegion entity){
        return MedTermRegionDto.builder()
                .regId(entity.getRegId())
                .region(entity.getRegion()).build();
    }

    public static MedTermLandForecastResponseDto medTermLandForecastToDto(MedTermLandForecast entity) {
        return MedTermLandForecastResponseDto.builder()
                .regId(entity.getRegId())
                .baseDateTime(entity.getBaseDateTime())
                .rnSt3Am(entity.getRnSt3Am())
                .rnSt3Pm(entity.getRnSt3Pm())
                .wf3Am(entity.getWf3Am())
                .wf3Pm(entity.getWf3Pm())
                .rnSt4Am(entity.getRnSt4Am())
                .rnSt4Pm(entity.getRnSt4Pm())
                .wf4Am(entity.getWf4Am())
                .wf4Pm(entity.getWf4Pm())
                .rnSt5Am(entity.getRnSt5Am())
                .rnSt5Pm(entity.getRnSt5Pm())
                .wf5Am(entity.getWf5Am())
                .wf5Pm(entity.getWf5Pm())
                .rnSt6Am(entity.getRnSt6Am())
                .rnSt6Pm(entity.getRnSt6Pm())
                .wf6Am(entity.getWf6Am())
                .wf6Pm(entity.getWf6Pm())
                .rnSt7Am(entity.getRnSt7Am())
                .rnSt7Pm(entity.getRnSt7Pm())
                .wf7Am(entity.getWf7Am())
                .wf7Pm(entity.getWf7Pm())
                .rnSt8(entity.getRnSt8())
                .wf8(entity.getWf8())
                .rnSt9(entity.getRnSt9())
                .wf9(entity.getWf9())
                .rnSt10(entity.getRnSt10())
                .wf10(entity.getWf10()).build();
    }

    public static MedTermTempForecastResponseDto medTermTempForecastToDto(MedTermTempForecast entity) {
        return MedTermTempForecastResponseDto.builder()
                .regId(entity.getRegId())
                .baseDateTime(entity.getBaseDateTime())
                .taMax3(entity.getTaMax3())
                .taMax3High(entity.getTaMax3High())
                .taMax3Low(entity.getTaMax3Low())
                .taMin3(entity.getTaMin3())
                .taMin3High(entity.getTaMin3High())
                .taMin3Low(entity.getTaMin3Low())
                .taMax4(entity.getTaMax4())
                .taMax4High(entity.getTaMax4High())
                .taMax4Low(entity.getTaMax4Low())
                .taMin4(entity.getTaMin4())
                .taMin4High(entity.getTaMin4High())
                .taMin4Low(entity.getTaMin4Low())
                .taMax5(entity.getTaMax5())
                .taMax5High(entity.getTaMax5High())
                .taMax5Low(entity.getTaMax5Low())
                .taMin5(entity.getTaMin5())
                .taMin5High(entity.getTaMin5High())
                .taMin5Low(entity.getTaMin5Low())
                .taMax6(entity.getTaMax6())
                .taMax6High(entity.getTaMax6High())
                .taMax6Low(entity.getTaMax6Low())
                .taMin6(entity.getTaMin6())
                .taMin6High(entity.getTaMin6High())
                .taMin6Low(entity.getTaMin6Low())
                .taMax7(entity.getTaMax7())
                .taMax7High(entity.getTaMax7High())
                .taMax7Low(entity.getTaMax7Low())
                .taMin7(entity.getTaMin7())
                .taMin7High(entity.getTaMin7High())
                .taMin7Low(entity.getTaMin7Low())
                .taMax8(entity.getTaMax8())
                .taMax8High(entity.getTaMax8High())
                .taMax8Low(entity.getTaMax8Low())
                .taMin8(entity.getTaMin8())
                .taMin8High(entity.getTaMin8High())
                .taMin8Low(entity.getTaMin8Low())
                .taMax9(entity.getTaMax9())
                .taMax9High(entity.getTaMax9High())
                .taMax9Low(entity.getTaMax9Low())
                .taMin9(entity.getTaMin9())
                .taMin9High(entity.getTaMin9High())
                .taMin9Low(entity.getTaMin9Low())
                .taMax10(entity.getTaMax10())
                .taMax10High(entity.getTaMax10High())
                .taMax10Low(entity.getTaMax10Low())
                .taMin10(entity.getTaMin10())
                .taMin10High(entity.getTaMin10High())
                .taMin10Low(entity.getTaMin10Low())
                .build();
    }


    public ShortTermForecastResponseDto ShortTermForecastToDto(ShortTermForecast entity){
        return ShortTermForecastResponseDto.builder()
                .regionCode(entity.getRegionCode())
                .baseDate(entity.getBaseDate())
                .baseTime(entity.getBaseTime())
                .fcstDate(entity.getFcstDate())
                .fcstTime(entity.getFcstTime())
                .POP(entity.getPop())
                .PTY(entity.getPty())
                .PCP(entity.getPcp())
                .REH(entity.getReh())
                .SNO(entity.getSno())
                .SKY(entity.getSky())
                .TMP(entity.getTmp())
                .TMN(entity.getTmn())
                .TMX(entity.getTmx())
                .UUU(entity.getUuu())
                .VVV(entity.getVvv())
                .WAV(entity.getWav())
                .VEC(entity.getVec())
                .WSD(entity.getWsd())
                .build();
    }

    public UltraShortTermForecastResponseDto ultraShortTermForecastToDto(UltraShortTermForecast entity) {
        return UltraShortTermForecastResponseDto.builder()
                .regionCode(entity.getRegionCode())
                .baseDate(entity.getBaseDate())
                .baseTime(entity.getBaseTime())
                .fcstDate(entity.getFcstDate())
                .fcstTime(entity.getFcstTime())
                .rn1(entity.getRn1())
                .sky(entity.getSky())
                .reh(entity.getReh())
                .uuu(entity.getUuu())
                .vvv(entity.getVvv())
                .vec(entity.getVec())
                .wsd(entity.getWsd())
                .t1h(entity.getT1h())
                .pty(entity.getPty())
                .lgt(entity.getLgt())
                .build();
    }



}
