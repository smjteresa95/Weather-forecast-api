package com.example.weatherforecast.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SkyCondition {
    CLEAR(1, "맑음"),
    MOSTLY_CLOUDY(3, "구름많음"),
    CLOUDY(4, "흐림");

    private final int code;
    private final String description;

    public static String getDescriptionByCode(int code) {
        for (SkyCondition type : values()) {
            if (type.getCode() == code) {
                return type.getDescription();
            }
        }
        return "데이터 없음";
    }
}
