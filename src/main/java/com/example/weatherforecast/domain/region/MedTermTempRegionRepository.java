package com.example.weatherforecast.domain.region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedTermTempRegionRepository extends JpaRepository<MedTermTempRegion, String>{

    @Query("SELECT m.region FROM MedTermTempRegion m WHERE m.regId = :regId")
    String findByRegId(@Param("regId") String regId);
}
