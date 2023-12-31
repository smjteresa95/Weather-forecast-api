package com.example.weatherforecast.web.dto;

import com.example.weatherforecast.domain.region.MedTermLandRegion;
import com.example.weatherforecast.domain.region.MedTermTempRegion;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class MedTermRegionDto {
    private String regId;
    private String region;

    public Object toEntity(String forecastType){
        if(Objects.equals(forecastType, "land")){
            return MedTermLandRegion.builder()
                    .regId(this.getRegId())
                    .region(this.getRegion())
                    .build();
        } else if (Objects.equals(forecastType, "temp")) {
            return MedTermTempRegion.builder()
                    .regId(this.getRegId())
                    .region(this.getRegion())
                    .build();
        }
        throw new IllegalArgumentException("Invalid forecast type: " + forecastType);
    }
}
