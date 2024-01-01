package com.example.weatherforecast.domain.medtermforecast;

import com.example.weatherforecast.web.dto.MedTermForecastRequestDto;
import com.example.weatherforecast.web.dto.MedTermLandForecastWithinAWeekJsonResponseDto;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class MedTermLandForecastRepositoryCustomImplTest {

    @Qualifier("medTermLandForecastRepositoryCustomImpl")
    @Autowired
    private MedTermLandForecastRepositoryCustom customRepository;
    @Autowired
    private MedTermLandForecastRepository repository;

    final String BASEDATE = "20231231";


    @Test
    void testFindDynamicForecastWithinAWeekByRegIdAndDateAndDayNumber(){

        // Given
        MedTermForecastRequestDto requestDto = new MedTermForecastRequestDto("11B00000", 3);

        MedTermLandForecast forecast = MedTermLandForecast.builder()
                .regId("11B00000")
                .baseDateTime(BASEDATE)
                .rnSt3Am(30)
                .rnSt3Pm(10)
                .rnSt8(20)
                .wf3Am("구름많음")
                .wf3Pm("맑음")
                .wf8("맑음")
                .build();
        repository.save(forecast);

        // When
        Optional<MedTermLandForecastWithinAWeekJsonResponseDto> result = customRepository.findDynamicForecastWithinAWeekByRegIdAndBaseDate(requestDto, BASEDATE);
        System.out.println("받아온 값: " + result.toString());

        // Then
        assertTrue(result.isPresent());
        assertEquals(30, result.get().getRnStAm());
    }
}