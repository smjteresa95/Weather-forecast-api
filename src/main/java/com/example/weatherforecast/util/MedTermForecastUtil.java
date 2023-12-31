package com.example.weatherforecast.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class MedTermForecastUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedTermForecastUtil.class);

    //현재 날짜와 가장 최근의 예보 발표일로 baseDateTime값 얻기/
    public String getBaseDateTime(){
        //시간 설정
        String pivotTime = determinePivotTimes();
        LOGGER.info("pivot time: {}", pivotTime);

        //tmFc 설정
        //requestDateTime 20231225 형태,
        //time은 0600 이나 1800, date은 오늘 날짜이다.
        String requestDateTime = getBaseDate() + pivotTime;
        LOGGER.info("dateTime: {}", requestDateTime);

        return requestDateTime;
    }

    //현재 시간을 기준으로 하되,
    //06:00 이전이면 전날 18:00
    //06:00 이후면 오늘 06:00
    //18:00 이전이면 오늘 06:00
    //18:00 이후면 오늘 18:00
    public String determinePivotTimes() {
        SimpleDateFormat formatter = new SimpleDateFormat("HHmm");
        String currentTime = formatter.format(new Date());

        String time;

        if (currentTime.compareTo("1800") < 0 || currentTime.compareTo("0600") > 0) {
            time = "0600";
        }
        else time = "1800";
        return time;
    }
    public String getBaseDate() {
        //시간
        SimpleDateFormat formatter = new SimpleDateFormat("HHmm");
        String currentTime = formatter.format(new Date());

        //날짜
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");

        if (currentTime.compareTo("0600") < 0) {
            // 오전 6시 이전 이면, 전날 날짜를 baseDate 으로 설정
            calendar.add(Calendar.HOUR_OF_DAY, -24);
            return dateFormatter.format(calendar.getTime());
        } else {
            return dateFormatter.format(calendar.getTime());
        }
    }
}
