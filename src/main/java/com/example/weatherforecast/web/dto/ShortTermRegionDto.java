package com.example.weatherforecast.web.dto;

import com.example.weatherforecast.domain.region.ShortTermRegion;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class ShortTermRegionDto {
    private String regionCode;
    private String level1;
    private String level2;
    private String level3;
    private int gridX;
    private int gridY;
    private double longitudeDegrees;
    private double longitudeMinutes;
    private double longitudeSeconds;
    private double latitudeDegrees;
    private double latitudeMinutes;
    private double latitudeSeconds;

    public ShortTermRegion toEntity(){
        return ShortTermRegion.builder()
                .regionCode(this.getRegionCode())
                .level1(this.getLevel1())
                .level2(this.getLevel2())
                .level3(this.getLevel3())
                .gridX(this.getGridX())
                .gridY(this.getGridY())
                .longitudeDegrees(this.getLongitudeDegrees())
                .longitudeMinutes(this.getLongitudeMinutes())
                .longitudeSeconds(this.getLongitudeSeconds())
                .latitudeDegrees(this.getLatitudeDegrees())
                .latitudeMinutes(this.getLatitudeMinutes())
                .latitudeSeconds(this.getLatitudeSeconds())
                .build();
    }
}
