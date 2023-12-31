package com.example.weatherforecast.domain.medtermforecast;

import com.example.weatherforecast.web.dto.MedTermForecastRequestDto;
import com.example.weatherforecast.web.dto.MedTermTempForecastJsonResponseDto;
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
public class MedTermTempForecastRepositoryCustomImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Qualifier("medTermTempForecastRepositoryCustomImpl")
    @Autowired
    private MedTermTempForecastRepositoryCustom repository;

    final String BASEDATETIME = "202312310060";
    final String REGID = "11D20501";

    @BeforeEach
    @Transactional
    void setUp(){
        MedTermTempForecast forecast = MedTermTempForecast.builder()
                .regId(REGID)
                .baseDateTime(BASEDATETIME)
                .taMax3(1)
                .taMax3High(2)
                .taMax3Low(0)
                .taMin3(3)
                .taMin3High(2)
                .taMin3Low(0)
                .build();
        entityManager.persist(forecast);
        entityManager.flush();
    }

    @Test
    void findDynamicTempForecastByRegIdAndBaseDateTest(){

        // Given
        MedTermForecastRequestDto requestDto = new MedTermForecastRequestDto(REGID, 3);

        // When
        Optional<MedTermTempForecastJsonResponseDto> result = repository.findDynamicTempForecastByRegIdAndBaseDateTime(requestDto, BASEDATETIME);
        System.out.println("받아온 값: " + result.toString());

        // Then
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getTaMax());
        assertEquals(0, result.get().getTaMaxLow());
    }


}
