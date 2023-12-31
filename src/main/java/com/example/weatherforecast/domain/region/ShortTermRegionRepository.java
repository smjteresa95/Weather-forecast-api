package com.example.weatherforecast.domain.region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortTermRegionRepository extends JpaRepository<ShortTermRegion, String> {

    @Query("SELECT r FROM ShortTermRegion r WHERE r.regionCode = :regionCode")
    Optional<ShortTermRegion> findRegionByCode(@Param("regionCode") String regionCode);

}
