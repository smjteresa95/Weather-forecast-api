# 테이블 구조

----

## 예보 데이터 명세

### MedTermLandForecast - 중기육상예보

| 필드명        | 데이터 타입 | 컬럼명          | 설명                   |
|---------------|-------------|-----------------|------------------------|
| id            | Long        | id              | 식별자                 |
| regId         | String      | reg_id          | 예보구역코드           |
| baseDateTime  | String      | base_date_time  | 기준 날짜와 발표시간   |
| rnSt3Am       | Integer     | rnst3_am        | 3일 후 오전 강수확률   |
| rnSt3Pm       | Integer     | rnst_3_pm       | 3일 후 오후 강수확률   |
| wf3Am         | String      | wf_3_am         | 3일 후 오전 날씨 예보  |
| wf3Pm         | String      | wf_3_pm         | 3일 후 오후 날씨 예보  |
| rnSt4Am       | Integer     | rnst4_am        | 4일 후 오전 강수확률   |
| rnSt4Pm       | Integer     | rnst4_pm        | 4일 후 오후 강수확률   |
| wf4Am         | String      | wf4_am          | 4일 후 오전 날씨 예보  |
| wf4Pm         | String      | wf4_pm          | 4일 후 오후 날씨 예보  |
| rnSt5Am       | Integer     | rnst5_am        | 5일 후 오전 강수확률   |
| rnSt5Pm       | Integer     | rnst5_pm        | 5일 후 오후 강수확률   |
| wf5Am         | String      | wf5_am          | 5일 후 오전 날씨 예보  |
| wf5Pm         | String      | wf5_pm          | 5일 후 오후 날씨 예보  |
| rnSt6Am       | Integer     | rnst6_am        | 6일 후 오전 강수확률   |
| rnSt6Pm       | Integer     | rnst6_pm        | 6일 후 오후 강수확률   |
| wf6Am         | String      | wf6_am          | 6일 후 오전 날씨 예보  |
| wf6Pm         | String      | wf6_pm          | 6일 후 오후 날씨 예보  |
| rnSt7Am       | Integer     | rnst7_am        | 7일 후 오전 강수확률   |
| rnSt7Pm       | Integer     | rnst7_pm        | 7일 후 오후 강수확률   |
| wf7Am         | String      | wf7_am          | 7일 후 오전 날씨 예보  |
| wf7Pm         | String      | wf7_pm          | 7일 후 오후 날씨 예보  |
| rnSt8         | Integer     | rnst8           | 8일 후 강수확률        |
| wf8           | String      | wf8             | 8일 후 날씨 예보       |
| rnSt9         | Integer     | rnst9           | 9일 후 강수확률        |
| wf9           | String      | wf9             | 9일 후 날씨 예보       |
| rnSt10        | Integer     | rnst10          | 10일 후 강수확률       |
| wf10          | String      | wf10            | 10일 후 날씨 예보      |


### MedTermTempForecast - 중기기온예보

| 필드명          | 데이터 타입 | 컬럼명         | 설명                             |
|-----------------|-------------|----------------|----------------------------------|
| id              | Long        | id             | 식별자                           |
| regId           | String      | reg_id         | 예보구역코드                     |
| baseDateTime    | String      | base_date_time | 기준 날짜와 예보발표시간          |
| taMax3          | Integer     | ta_max3        | 3일 후 예상최저기온(℃)           |
| taMax3High      | Integer     | ta_max3_high   | 3일 후 예상최저기온 하한 범위    |
| taMax3Low       | Integer     | ta_max3_low    | 3일 후 예상최저기온 상한 범위    |
| taMin3          | Integer     | ta_min3        | 3일 후 예상최고기온(℃)           |
| taMin3High      | Integer     | ta_min3_high   | 3일 후 예상최고기온 하한 범위    |
| taMin3Low       | Integer     | ta_min3_low    | 3일 후 예상최고기온 상한 범위    |
| taMax4          | Integer     | ta_max4        | 4일 후 예상최저기온(℃)           |
| taMax4High      | Integer     | ta_max4_high   | 4일 후 예상최저기온 하한 범위    |
| taMax4Low       | Integer     | ta_max4_low    | 4일 후 예상최저기온 상한 범위    |
| taMin4          | Integer     | ta_min4        | 4일 후 예상최고기온(℃)           |
| taMin4High      | Integer     | ta_min4_high   | 4일 후 예상최고기온 하한 범위    |
| taMin4Low       | Integer     | ta_min4_low    | 4일 후 예상최고기온 상한 범위    |
| ...             | ...         | ...            | ...                              |
| taMax10         | Integer     | ta_max10       | 10일 후 예상최저기온(℃)          |
| taMax10High     | Integer     | ta_max10_high  | 10일 후 예상최저기온 하한 범위   |
| taMax10Low      | Integer     | ta_max10_low   | 10일 후 예상최저기온 상한 범위   |
| taMin10         | Integer     | ta_min10       | 10일 후 예상최고기온(℃)          |
| taMin10High     | Integer     | ta_min10_high  | 10일 후 예상최고기온 하한 범위   |
| taMin10Low      | Integer     | ta_min10_low   | 10일 후 예상최고기온 상한 범위   |

### ShortTermForecast - 단기예보

| 필드명                        | 데이터 타입    | 컬럼명                     | 설명                    |
|-------------------------------|---------------|---------------------------|-------------------------|
| id                            | Long          | id                        | 식별자                  |
| regionCode                    | String        | region_code               | 행정구역코드            |
| baseDate                      | String        | base_date                 | 발표일자                |
| baseTime                      | String        | base_time                 | 발표시각                |
| fcstDate                      | String        | fcst_date                 | 예보일자                |
| fcstTime                      | String        | fcst_time                 | 예보시각                |
| pop                           | String        | precipitation_probability | 강수확률 (%)            |
| pty                           | String        | precipitation_type        | 강수형태 (코드값)       |
| pcp                           | String        | hourly_precipitation      | 1시간 강수량 (범주 1 mm)|
| reh                           | BigDecimal    | humidity                  | 습도                    |
| sno                           | BigDecimal    | hourly_new_snow           | 1시간 신적설 (범주 1 cm)|
| sky                           | String        | sky_condition             | 하늘상태 (코드값)       |
| tmp                           | BigDecimal    | hourly_temperature        | 1시간 기온 (℃)         |
| tmn                           | BigDecimal    | min_temperature           | 일 최저기온 (℃)        |
| tmx                           | BigDecimal    | max_temperature           | 일 최고기온 (℃)        |
| uuu                           | BigDecimal    | east_west_wind_component  | 동서바람성분           |
| vvv                           | BigDecimal    | north_south_wind_component| 남북바람성분           |
| wav                           | BigDecimal    | wave_height               | 파고 (M)               |
| vec                           | BigDecimal    | wind_direction            | 풍향                   |
| wsd                           | BigDecimal    | wind_speed                | 풍속                   |

### UltraShortTermForecast = 초단기예보

| 필드명                       | 데이터 타입    | 컬럼명                    | 설명                    |
|------------------------------|---------------|--------------------------|------------------------|
| id                           | Long          | id                       | 식별자                  |
| regionCode                   | String        | region_code              | 행정구역코드            |
| baseDate                     | String        | base_date                | 발표일자                |
| baseTime                     | String        | base_time                | 발표시각                |
| fcstDate                     | String        | fcst_date                | 예보일자                |
| fcstTime                     | String        | fcst_time                | 예보시각                |
| rn1                          | BigDecimal    | hourly_precipitation     | 1시간 강수량 (범주 1 mm)|
| sky                          | BigDecimal    | sky_condition            | 하늘상태 (코드값)       |
| reh                          | BigDecimal    | humidity                 | 습도                    |
| uuu                          | BigDecimal    | east_west_wind_component | 동서바람성분           |
| vvv                          | BigDecimal    | north_south_wind_component| 남북바람성분          |
| vec                          | BigDecimal    | wind_direction           | 풍향                   |
| wsd                          | BigDecimal    | wind_speed               | 풍속                   |
| t1h                          | BigDecimal    | temperature              | 기온                    |
| pty                          | BigDecimal    | precipitation_type       | 강수형태 코드값         |
| lgt                          | BigDecimal    | lightning                | 낙뢰                    |

----

## 지역코드 테이블 명세

### MedTermLandRegion - 중기육상예보 지역코드
| 필드명  | 데이터 타입 | 컬럼명  | 설명         |
|---------|-------------|--------|--------------|
| regId   | String      | reg_id | 예보구역코드 |
| region  | String      | region | 지역명       |


### MedTermLandRegion - 중기기온예보 지역코드
| 필드명  | 데이터 타입 | 컬럼명  | 설명         |
|---------|-------------|--------|--------------|
| regId   | String      | reg_id | 예보구역코드 |
| region  | String      | region | 지역명       |

### ShortTermRegion - 단기예보 지역코드

| 필드명             | 데이터 타입 | 컬럼명              | 설명                          |
|-------------------|-------------|---------------------|------------------------------|
| regionCode        | String      | region_code         | 지역 코드 (고유)              |
| level1            | String      | level1              | 지역 분류 레벨 1              |
| level2            | String      | level2              | 지역 분류 레벨 2              |
| level3            | String      | level3              | 지역 분류 레벨 3              |
| gridX             | int         | grid_x              | 격자 X 좌표                   |
| gridY             | int         | grid_y              | 격자 Y 좌표                   |
| longitudeDegrees  | double      | longitude_degrees   | 경도 (도)                     |
| longitudeMinutes  | double      | longitude_minutes   | 경도 (분)                     |
| longitudeSeconds  | double      | longitude_seconds   | 경도 (초)                     |
| latitudeDegrees   | double      | latitude_degrees    | 위도 (도)                     |
| latitudeMinutes   | double      | latitude_minutes    | 위도 (분)                     |
| latitudeSeconds   | double      | latitude_seconds    | 위도 (초)                     |
