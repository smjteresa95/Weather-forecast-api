package com.example.weatherforecast.domain.region;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class MedTermLandRegionRepositoryTest {

    @Autowired
    MedTermLandRegionRepository repository;

    final String REGID = "11B10101";
    final String REGION = "서울";

    @BeforeEach
    public void setUp(){
        MedTermLandRegion region = MedTermLandRegion.builder()
                .region(REGION)
                .regId(REGID)
                .build();
        repository.save(region);
    }

    @Test
    public void findByRegIdTest(){
        //when
        String region = repository.findByRegId(REGID);
        //then
        assertThat(region).isEqualTo(REGION);
    }



}
