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
@AllArgsConstructor
@Builder
@Entity
@Table(name = "med_term_land_region")
public class MedTermLandRegion {

    @Id
    @Column(name = "reg_id", unique = true)
    private String regId;

    @Column(name = "region")
    private String region;
}
