package com.example.weatherforecast.domain.medtermforecast;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedTermTempForecastRepository extends JpaRepository<MedTermTempForecast, Long>, MedTermTempForecastRepositoryCustom {

    Optional<MedTermTempForecast> findByRegIdAndBaseDateTime(String regId, String baseDateTime);

    @Transactional
    @Modifying
    @Query("UPDATE MedTermTempForecast m SET " +
            "m.taMax3 = :taMax3, m.taMax3High = :taMax3High, m.taMax3Low = :taMax3Low, m.taMin3 = :taMin3, m.taMin3High = :taMin3High, m.taMin3Low = :taMin3Low, " +
            "m.taMax4 = :taMax4, m.taMax4High = :taMax4High, m.taMax4Low = :taMax4Low, m.taMin4 = :taMin4, m.taMin4High = :taMin4High, m.taMin4Low = :taMin4Low, " +
            "m.taMax5 = :taMax5, m.taMax5High = :taMax5High, m.taMax5Low = :taMax5Low, m.taMin5 = :taMin5, m.taMin5High = :taMin5High, m.taMin5Low = :taMin5Low, " +
            "m.taMax6 = :taMax6, m.taMax6High = :taMax6High, m.taMax6Low = :taMax6Low, m.taMin6 = :taMin6, m.taMin6High = :taMin6High, m.taMin6Low = :taMin6Low, " +
            "m.taMax7 = :taMax7, m.taMax7High = :taMax7High, m.taMax7Low = :taMax7Low, m.taMin7 = :taMin7, m.taMin7High = :taMin7High, m.taMin7Low = :taMin7Low, " +
            "m.taMax8 = :taMax8, m.taMax8High = :taMax8High, m.taMax8Low = :taMax8Low, m.taMin8 = :taMin8, m.taMin8High = :taMin8High, m.taMin8Low = :taMin8Low, " +
            "m.taMax9 = :taMax9, m.taMax9High = :taMax9High, m.taMax9Low = :taMax9Low, m.taMin9 = :taMin9, m.taMin9High = :taMin9High, m.taMin9Low = :taMin9Low, " +
            "m.taMax10 = :taMax10, m.taMax10High = :taMax10High, m.taMax10Low = :taMax10Low, m.taMin10 = :taMin10, m.taMin10High = :taMin10High, m.taMin10Low = :taMin10Low " +
            "WHERE m.regId = :regId AND m.baseDateTime = :baseDateTime")
    void updateTempForecastByRegIdAndBaseDateTime(@Param("regId") String regId,
                                                  @Param("baseDateTime") String baseDateTime,
                                                  @Param("taMax3") Integer taMax3,
                                                  @Param("taMax3High") Integer taMax3High,
                                                  @Param("taMax3Low") Integer taMax3Low,
                                                  @Param("taMin3") Integer taMin3,
                                                  @Param("taMin3High") Integer taMin3High,
                                                  @Param("taMin3Low") Integer taMin3Low,
                                                  @Param("taMax4") Integer taMax4,
                                                  @Param("taMax4High") Integer taMax4High,
                                                  @Param("taMax4Low") Integer taMax4Low,
                                                  @Param("taMin4") Integer taMin4,
                                                  @Param("taMin4High") Integer taMin4High,
                                                  @Param("taMin4Low") Integer taMin4Low,
                                                  @Param("taMax5") Integer taMax5,
                                                  @Param("taMax5High") Integer taMax5High,
                                                  @Param("taMax5Low") Integer taMax5Low,
                                                  @Param("taMin5") Integer taMin5,
                                                  @Param("taMin5High") Integer taMin5High,
                                                  @Param("taMin5Low") Integer taMin5Low,
                                                  @Param("taMax6") Integer taMax6,
                                                  @Param("taMax6High") Integer taMax6High,
                                                  @Param("taMax6Low") Integer taMax6Low,
                                                  @Param("taMin6") Integer taMin6,
                                                  @Param("taMin6High") Integer taMin6High,
                                                  @Param("taMin6Low") Integer taMin6Low,
                                                  @Param("taMax7") Integer taMax7,
                                                  @Param("taMax7High") Integer taMax7High,
                                                  @Param("taMax7Low") Integer taMax7Low,
                                                  @Param("taMin7") Integer taMin7,
                                                  @Param("taMin7High") Integer taMin7High,
                                                  @Param("taMin7Low") Integer taMin7Low,
                                                  @Param("taMax8") Integer taMax8,
                                                  @Param("taMax8High") Integer taMax8High,
                                                  @Param("taMax8Low") Integer taMax8Low,
                                                  @Param("taMin8") Integer taMin8,
                                                  @Param("taMin8High") Integer taMin8High,
                                                  @Param("taMin8Low") Integer taMin8Low,
                                                  @Param("taMax9") Integer taMax9,
                                                  @Param("taMax9High") Integer taMax9High,
                                                  @Param("taMax9Low") Integer taMax9Low,
                                                  @Param("taMin9") Integer taMin9,
                                                  @Param("taMin9High") Integer taMin9High,
                                                  @Param("taMin9Low") Integer taMin9Low,
                                                  @Param("taMax10") Integer taMax10,
                                                  @Param("taMax10High") Integer taMax10High,
                                                  @Param("taMax10Low") Integer taMax10Low,
                                                  @Param("taMin10") Integer taMin10,
                                                  @Param("taMin10High") Integer taMin10High,
                                                  @Param("taMin10Low") Integer taMin10Low);
}
