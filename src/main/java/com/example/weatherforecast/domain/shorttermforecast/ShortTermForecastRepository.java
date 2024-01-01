package com.example.weatherforecast.domain.shorttermforecast;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ShortTermForecastRepository extends JpaRepository<ShortTermForecast, Long> {

    @Query("SELECT s FROM ShortTermForecast s WHERE s.baseDate = :baseDate AND s.baseTime = :baseTime AND s.regionCode = :regionCode")
    Optional<ShortTermForecast> findByMatchingCriteria(@Param("baseDate") String baseDate,
                                                             @Param("baseTime") String baseTime,
                                                             @Param("regionCode") String regionCode);

    @Query("SELECT s.id FROM ShortTermForecast s WHERE s.baseDate = :baseDate AND s.baseTime = :baseTime AND s.regionCode = :regionCode")
    Optional<Long> findIdByBaseDateAndBaseTimeAndRegionCode(@Param("baseDate") String baseDate,
                                                            @Param("baseTime") String baseTime,
                                                            @Param("regionCode") String regionCode);

    @Transactional
    @Modifying
    @Query("UPDATE ShortTermForecast s SET s.fcstDate = :fcstDate, s.fcstTime = :fcstTime, s.pcp = :pcp, s.sky = :sky, s.reh = :reh, s.uuu = :uuu, s.vvv = :vvv, s.vec = :vec, s.wsd = :wsd, s.pop = :pop, s.pty = :pty, s.sno = :sno, s.tmp = :tmp, s.tmn = :tmn, s.tmx = :tmx, s.wav = :wav " +
            "WHERE s.baseDate = :baseDate AND s.baseTime = :baseTime AND s.regionCode = :regionCode")
    void updateShortTermForecast(@Param("regionCode") String regionCode,
                                 @Param("baseDate") String baseDate,
                                 @Param("baseTime") String baseTime,
                                 @Param("fcstDate") String fcstDate,
                                 @Param("fcstTime") String fcstTime,
                                 @Param("pop") String pop,
                                 @Param("pcp") String pcp,
                                 @Param("pty") String pty,
                                 @Param("reh") BigDecimal reh,
                                 @Param("sno") BigDecimal sno,
                                 @Param("sky") String sky,
                                 @Param("tmp") BigDecimal tmp,
                                 @Param("tmn") BigDecimal tmn,
                                 @Param("tmx") BigDecimal tmx,
                                 @Param("uuu") BigDecimal uuu,
                                 @Param("vvv") BigDecimal vvv,
                                 @Param("wav") BigDecimal wav,
                                 @Param("vec") BigDecimal vec,
                                 @Param("wsd") BigDecimal wsd
                                );

}
