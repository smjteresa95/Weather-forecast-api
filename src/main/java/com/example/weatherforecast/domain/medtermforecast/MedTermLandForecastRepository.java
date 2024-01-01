package com.example.weatherforecast.domain.medtermforecast;

import com.example.weatherforecast.web.dto.MedTermLandForecastResponseDto;
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
    @Query("UPDATE MedTermLandForecast m SET m.rnSt3Am = :#{#dto.rnSt3Am}, m.rnSt3Pm = :#{#dto.rnSt3Pm}, m.wf3Am = :#{#dto.wf3Am}, m.wf3Pm = :#{#dto.wf3Pm}, " +
            "m.rnSt4Am = :#{#dto.rnSt4Am}, m.rnSt4Pm = :#{#dto.rnSt4Pm}, m.wf4Am = :#{#dto.wf4Am}, m.wf4Pm = :#{#dto.wf4Pm}, " +
            "m.rnSt5Am = :#{#dto.rnSt5Am}, m.rnSt5Pm = :#{#dto.rnSt5Pm}, m.wf5Am = :#{#dto.wf5Am}, m.wf5Pm = :#{#dto.wf5Pm}, " +
            "m.rnSt6Am = :#{#dto.rnSt6Am}, m.rnSt6Pm = :#{#dto.rnSt6Pm}, m.wf6Am = :#{#dto.wf6Am}, m.wf6Pm = :#{#dto.wf6Pm}, " +
            "m.rnSt7Am = :#{#dto.rnSt7Am}, m.rnSt7Pm = :#{#dto.rnSt7Pm}, m.wf7Am = :#{#dto.wf7Am}, m.wf7Pm = :#{#dto.wf7Pm}, " +
            "m.rnSt8 = :#{#dto.rnSt8}, m.wf8 = :#{#dto.wf8}, m.rnSt9 = :#{#dto.rnSt9}, m.wf9 = :#{#dto.wf9}, " +
            "m.rnSt10 = :#{#dto.rnSt10}, m.wf10 = :#{#dto.wf10} WHERE m.regId = :#{#dto.regId} AND m.baseDateTime = :#{#dto.baseDateTime}")
    void updateByRegIdAndBaseDateTime(@Param("dto") MedTermLandForecast forecast);

}
