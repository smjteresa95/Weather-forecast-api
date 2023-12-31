package com.example.weatherforecast.domain.region;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor @Builder
@Entity
@Table(name = "short_term_region")
public class ShortTermRegion {

    @Id
    @Column(name = "region_code", unique = true)
    private String regionCode;

    @Column(name = "level1")
    private String level1;

    @Column(name = "level2")
    private String level2;

    @Column(name = "level3")
    private String level3;

    @Column(name = "grid_x")
    private int gridX;

    @Column(name = "grid_y")
    private int gridY;

    @Column(name = "longitude_degrees")
    private double longitudeDegrees;

    @Column(name = "longitude_minutes")
    private double longitudeMinutes;

    @Column(name = "longitude_seconds")
    private double longitudeSeconds;

    @Column(name = "latitude_degrees")
    private double latitudeDegrees;

    @Column(name = "latitude_minutes")
    private double latitudeMinutes;

    @Column(name = "latitude_seconds")
    private double latitudeSeconds;

}
