package com.example.weatherforecast.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PrecipitationType {
    NONE(0, "없음"),
    RAIN(1, "비"),
    RAIN_SNOW(2, "비/눈"),
    SNOW(3, "눈"),
    SHOWER(4, "소나기"),
    RAIN_DROP(5, "빗방울"),
    RAIN_DROP_SNOW_FLURRY(6, "빗방울눈날림"),
    SNOW_FLURRY(7, "눈날림");

    private final int code;
    private final String description;

    public static String getDescriptionByCode(int code) {
        for (PrecipitationType type : values()) {
            if (type.getCode() == code) {
                return type.getDescription();
            }
        }
        return "데이터 없음";
    }
}
