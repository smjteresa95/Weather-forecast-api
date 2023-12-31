package com.example.weatherforecast.domain.medtermforecast;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedTermLandForecastRepository extends JpaRepository<MedTermLandForecast, Long>, MedTermLandForecastRepositoryCustom {

    Optional<MedTermLandForecast> findByRegIdAndBaseDateTime(String regId, String baseDateTime);

    @Transactional
    @Modifying
    @Query("UPDATE MedTermLandForecast m SET m.rnSt3Am = :rnSt3Am, m.rnSt3Pm = :rnSt3Pm, m.wf3Am = :wf3Am, m.wf3Pm = :wf3Pm, " +
            "m.rnSt4Am = :rnSt4Am, m.rnSt4Pm = :rnSt4Pm, m.wf4Am = :wf4Am, m.wf4Pm = :wf4Pm, " +
            "m.rnSt5Am = :rnSt5Am, m.rnSt5Pm = :rnSt5Pm, m.wf5Am = :wf5Am, m.wf5Pm = :wf5Pm, " +
            "m.rnSt6Am = :rnSt6Am, m.rnSt6Pm = :rnSt6Pm, m.wf6Am = :wf6Am, m.wf6Pm = :wf6Pm, " +
            "m.rnSt7Am = :rnSt7Am, m.rnSt7Pm = :rnSt7Pm, m.wf7Am = :wf7Am, m.wf7Pm = :wf7Pm, " +
            "m.rnSt8 = :rnSt8, m.wf8 = :wf8, m.rnSt9 = :rnSt9, m.wf9 = :wf9, " +
            "m.rnSt10 = :rnSt10, m.wf10 = :wf10 WHERE m.regId = :regId AND m.baseDateTime = :baseDateTime")
    void updateByRegIdAndBaseDateTime(@Param("regId") String regId,
                                      @Param("baseDateTime") String baseDateTime,
                                      @Param("rnSt3Am") Integer rnSt3Am,
                                      @Param("rnSt3Pm") Integer rnSt3Pm,
                                      @Param("wf3Am") String wf3Am,
                                      @Param("wf3Pm") String wf3Pm,
                                      @Param("rnSt4Am") Integer rnSt4Am,
                                      @Param("rnSt4Pm") Integer rnSt4Pm,
                                      @Param("wf4Am") String wf4Am,
                                      @Param("wf4Pm") String wf4Pm,
                                      @Param("rnSt5Am") Integer rnSt5Am,
                                      @Param("rnSt5Pm") Integer rnSt5Pm,
                                      @Param("wf5Am") String wf5Am,
                                      @Param("wf5Pm") String wf5Pm,
                                      @Param("rnSt6Am") Integer rnSt6Am,
                                      @Param("rnSt6Pm") Integer rnSt6Pm,
                                      @Param("wf6Am") String wf6Am,
                                      @Param("wf6Pm") String wf6Pm,
                                      @Param("rnSt7Am") Integer rnSt7Am,
                                      @Param("rnSt7Pm") Integer rnSt7Pm,
                                      @Param("wf7Am") String wf7Am,
                                      @Param("wf7Pm") String wf7Pm,
                                      @Param("rnSt8") Integer rnSt8,
                                      @Param("wf8") String wf8,
                                      @Param("rnSt9") Integer rnSt9,
                                      @Param("wf9") String wf9,
                                      @Param("rnSt10") Integer rnSt10,
                                      @Param("wf10") String wf10
    );

}
