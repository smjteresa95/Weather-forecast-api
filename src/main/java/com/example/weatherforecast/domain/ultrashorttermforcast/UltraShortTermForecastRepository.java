package com.example.weatherforecast.domain.ultrashorttermforcast;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface UltraShortTermForecastRepository extends JpaRepository<UltraShortTermForecast, Long> {

    //JSON 데이터 반환하기 위한 쿼리문
    //fcstTime을 기준으로, regionCode, baseDate, baseTime이 동일한 데이터 찾기
    @Query("SELECT u FROM UltraShortTermForecast u WHERE u.regionCode = :regionCode AND u.baseDate = :baseDate AND u.fcstTime = :fcstTime")
    Optional<UltraShortTermForecast> findByRegionCodeAndBaseDateAndBaseTimeAndFcstTime(
            @Param("regionCode") String regionCode,
            @Param("baseDate") String baseDate,
            @Param("fcstTime") String fcstTime);

    @Query("SELECT u FROM UltraShortTermForecast u WHERE u.baseDate = :baseDate AND u.baseTime = :baseTime AND u.fcstDate = :fcstDate AND u.fcstTime = :fcstTime AND u.regionCode = :regionCode")
    Optional<UltraShortTermForecast> findByBaseDateAndBaseTimeAndFcstDateAndFcstTimeAndRegionCode(
            @Param("baseDate") String baseDate,
            @Param("baseTime") String baseTime,
            @Param("fcstDate") String fcstDate,
            @Param("fcstTime") String fcstTime,
            @Param("regionCode") String regionCode);

    @Transactional
    @Modifying
    @Query("UPDATE UltraShortTermForecast u SET u.rn1 = :rn1, u.sky = :sky, u.reh = :reh, u.uuu = :uuu, u.vvv = :vvv, u.vec = :vec, u.wsd = :wsd, u.t1h = :t1h, u.pty = :pty, u.lgt = :lgt WHERE u.regionCode = :regionCode AND u.baseDate = :baseDate AND u.baseTime = :baseTime AND u.fcstDate = :fcstDate AND u.fcstTime = :fcstTime")
    void updateUltraShortTermForecast(@Param("regionCode") String regionCode,
                                      @Param("baseDate") String baseDate,
                                      @Param("baseTime") String baseTime,
                                      @Param("fcstDate") String fcstDate,
                                      @Param("fcstTime") String fcstTime,
                                      @Param("rn1") BigDecimal rn1,
                                      @Param("sky") BigDecimal sky,
                                      @Param("reh") BigDecimal reh,
                                      @Param("uuu") BigDecimal uuu,
                                      @Param("vvv") BigDecimal vvv,
                                      @Param("vec") BigDecimal vec,
                                      @Param("wsd") BigDecimal wsd,
                                      @Param("t1h") BigDecimal t1h,
                                      @Param("pty") BigDecimal pty,
                                      @Param("lgt") BigDecimal lgt);

}
