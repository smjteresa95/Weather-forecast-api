package com.example.weatherforecast.service;

import com.example.weatherforecast.domain.region.ShortTermRegion;
import com.example.weatherforecast.domain.region.ShortTermRegionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class RegionDataServiceTest {

    @Mock
    private ShortTermRegionRepository repository;
    @InjectMocks
    private RegionDataService regionDataService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    public void loadAndSaveShortTermRegionDataTest(){

        String mockRegionCode = "1100000000";
        ShortTermRegion mockEntity = ShortTermRegion.builder()
                .regionCode(mockRegionCode)
                .level1("서울특별시")
                .gridX(60)
                .gridY(127)
                .longitudeDegrees(126)
                .longitudeMinutes(58)
                .longitudeSeconds(48.03)
                .latitudeDegrees(37)
                .latitudeMinutes(33)
                .latitudeSeconds(48.85)
                .build();

        when(repository.save(mockEntity)).thenReturn(mockEntity);
        when(repository.findRegionByCode(mockRegionCode)).thenReturn(Optional.of(mockEntity));

        regionDataService.loadAndSaveShortTermRegionData();
        Optional<ShortTermRegion> retrievedRegion = repository.findRegionByCode(mockRegionCode);

        assertThat(retrievedRegion.isPresent()).isTrue();
        assertThat(retrievedRegion.get().getLatitudeDegrees()).isEqualTo(mockEntity.getLatitudeDegrees());
    }

}
