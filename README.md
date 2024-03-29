# [기상청  OPEN API를 이용한 날씨 예보 저장 및 조회 API](https://smjteresa95.github.io/Weather-forecast-api/)

단기예보, 초단기예보, 중기예보(육상예보, 기온예보) 데이터를 가지고 와 DB에 저장/조회 할 수 있습니다.

### [DB 테이블 명세](https://smjteresa95.github.io/Weather-forecast-api/src/main/docs/DESCRIPTION.html)


----

## [API 문서](https://smjteresa95.github.io/Weather-forecast-api/src/main/resources/static/html/docs/index.html)
- [API 명세 문서](https://smjteresa95.github.io/Weather-forecast-api/src/main/resources/static/html/docs/index.html)   
- http://localhost:8080/docs/index.html

### Spring REST doc snippet의 생성
controller 테스트 코드를 돌리면 `target/generated-snippets` 경로에 자동으로 snippet이 생성된다.

### Spring REST doc
- html 파일의 경로 : `src/main/resources/static/html/docs`
- 생성된 snippet을 연결한 .adoc 파일들의 경로 : `src/main/docs/asciidoc`

----

## [기상청 예보 조회 서비스 OPEN API의 활용]
### 1. 중기예보 기상청 OPEN API
**데이터 갱신주기:**  
06시, 18시(일 2회), 최근 24시간 자료만 제공

**요청명세**

| 항목명(영문) | 항목명(국문)      | 항목크기 | 항목구분 | 샘플데이터    |
|--------------|-----------------|----------|----------|---------------|
| serviceKey   | 인증키(URL Encode)           | 100      | 1        | 인증키        |
| numOfRows    | 한 페이지 결과 수 | 4        | 1        | 10            |
| pageNo       | 페이지 번호      | 4        | 1        | 1             |
| dataType     | 응답자료형식     | 4        | 0        | XML           |
| regId        | 예보구역코드     | 8        | 1        | 11B00000      |
| tmFc         | 발표시각         | 12       | 1        | 201307300600  |

### - 중기육상예보
예보구역코드, 발표시각의 조회 조건으로 예보일로부터 3일에서 10일까지 육상날씨정보를 조회하는 기능

**요청예시**  
`
http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst?serviceKey=인증키&numOfRows=10&pageNo=1&regId=11B00000&tmFc=201310171800
`
### - 중기기온예보  
예보구역코드, 발표시각의 조회 조건으로 예보일로부터 3일에서 10일까지 최저/최고기온정보를 조회하는 기능

**요청예시**   
`http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa?serviceKey=인증키&numOfRows=10&pageNo=1&regId=11D20501&tmFc=201404080600`

## 2. 단기예보 기상청 OPEN API

### 코드값 및 범주
- 하늘상태(SKY) 코드 :    
맑음(1), 구름많음(3), 흐림(4)         


- 강수형태(PTY) 코드 :    
(초단기) 없음(0), 비(1), 비/눈(2), 눈(3), 빗방울(5), 빗방울눈날림(6), 눈날림(7)   
(단기) 없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4)


- 초단기예보, 단기예보 강수량(RN1, PCP) 범주 및 표시방법(값)  

  | 범주                         | 문자열표시       |
  |-----------------------------|----------------|
  | 0.1 ~ 1.0mm 미만             | 1.0mm 미만       |
  | 1.0mm 이상 ~ 30.0mm 미만      | 실수값+mm        |
  | (1.0mm ~ 29.9mm)            |                 |
  | 30.0 mm 이상 ~ 50.0 mm 미만  | 30.0~50.0mm      |
  | 50.0 mm 이상                 | 50.0mm 이상      |
※ -, null, 0값은 ‘강수없음

- 신적설(SNO) 범주 및 표시방법(값)    

  | 범주                  | 문자열표시   |
  |-----------------------|--------------|
  | 0.1 ~ 1.0cm 미만      | 1.0cm 미만   |
  | 1.0cm 이상 ~ 5.0cm 미만| 실수값+cm    |
  | (1.0cm ~ 4.9cm)       |              |
  | 5.0 cm 이상           | 5.0cm 이상   |
※ -, null, 0값은 ‘적설없음’

- 낙뢰코드(LGT) 정보    
  낙뢰(초단기예보) : 에너지밀도(0.2~100kA(킬로암페어)/㎢)


- 풍속 정보   
  동서바람성분(UUU) : 동(+표기), 서(-표기)   
  남북바람성분(VVV) : 북(+표기), 남(-표기)    

### 예보요소 규칙
- 하늘상태단위

| 하늘상태  | 전운량     |
|----------|------------|
| 맑음     | 0 ～ 5     |
| 구름많음 | 6 ～ 8     |
| 흐림     | 9 ～ 10    |

- 풍향구간별 표현단위

| 풍향 구간(°) | 표현 단위 | 풍향 구간(°) | 표현 단위 |
|-------------|-----------|-------------|-----------|
| 0 – 45      | N-NE      | 180 – 225   | S-SW      |
| 45 – 90     | NE-E      | 225 – 270   | SW-W      |
| 90 – 135    | E-SE      | 270 – 315   | W-NW      |
| 135 – 180   | SE-S      | 315 – 360   | NW-N      |

- 풍향값에 따른 16방위 변환식   
  (풍향값 + 22.5 * 0.5) / 22.5) = 변환값(소수점 이하 버림)

| 변환값 | 16방위 |
|--------|--------|
| 0      | N      |
| 1      | NNE    |
| 2      | NE     |
| 3      | ENE    |
| 4      | E      |
| 5      | ESE    |
| 6      | SE     |
| 7      | SSE    |
| 8      | S      |
| 9      | SSW    |
| 10     | SW     |
| 11     | WSW    |
| 12     | W      |
| 13     | WNW    |
| 14     | NW     |
| 15     | NNW    |
| 16     | N      |


### - 단기예보
**조회서비스 발표시각:**
0200, 0500, 0800, 1100, 1400, 1700, 2000, 2300 (1일 8회)    

**요청예시**    
`http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey=%EC%9D%B8%EC%A6%9D%ED%82%A4&numOfRows=10&pageNo=1&base_date=20210628&base_time=0500&nx=55&ny=127`

**요청명세**

| 항목명(영문)  | 항목명(국문)     | 항목크기 | 항목구분 | 샘플데이터   |
|--------------|-----------------|----------|----------|-------------|
| numOfRows    | 한 페이지 결과 수 | 4        | 1        | 50          |
| pageNo       | 페이지 번호      | 4        | 1        | 1           |
| totalCount   | 데이터 총 개수   | 10       | 1        | 1           |
| resultCode   | 응답메시지 코드  | 2        | 1        | 00          |
| resultMsg    | 응답메시지 내용  | 100      | 1        | NORMAL SERVICE |
| dataType     | 데이터 타입      | 4        | 1        | XML         |
| baseDate     | 발표일자         | 8        | 1        | 20210628    |
| baseTime     | 발표시각         | 6        | 1        | 0500        |
| fcstDate     | 예보일자         | 8        | 1        | 20210628    |
| fcstTime     | 예보시각         | 4        | 1        | 0600        |
| category     | 자료구분문자     | 3        | 1        | TMP         |
| fcstValue    | 예보 값         | 2        | 1        | 21          |
| nx           | 예보지점 X 좌표  | 2        | 1        | 55          |
| ny           | 예보지점 Y 좌표  | 2        | 1        | 127         |


### - 초단기예보
**조회서비스 발표시각:**   
매시간 30분에 생성되고 10분마다 최신 정보로 업데이트(기온, 습도, 바람)    

**요청예시**     
`http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst?serviceKey=%EC%9D%B8%EC%A6%9D%ED%82%A4&numOfRows=10&pageNo=1&base_date=20210628&base_time=0630&nx=55&ny=127`

**요청명세**

| 항목명(영문) | 항목명(국문)      | 항목크기 | 항목구분 | 샘플데이터 |
|--------------|------------------|----------|----------|------------|
| serviceKey   | 인증키(URL Encode)           | 100      | 1        | 인증키(URL Encode)     |
| numOfRows    | 한 페이지 결과 수 | 4        | 1        | 10         |
| pageNo       | 페이지 번호      | 4        | 1        | 1          |
| dataType     | 응답자료형식     | 4        | 0        | XML        |
| base_date    | 발표일자(필수)    | 8        | 1        | 20210628   |
| base_time    | 발표시각(필수)    | 4        | 1        | 0630       |
| nx           | 예보지점 X 좌표(필수) | 2    | 1        | 55         |
| ny           | 예보지점 Y 좌표(필수) | 2    | 1        | 127        |



