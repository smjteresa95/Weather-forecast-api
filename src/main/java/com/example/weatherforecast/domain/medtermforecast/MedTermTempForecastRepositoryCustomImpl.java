package com.example.weatherforecast.domain.medtermforecast;

import com.example.weatherforecast.web.dto.MedTermForecastRequestDto;
import com.example.weatherforecast.web.dto.MedTermTempForecastJsonResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MedTermTempForecastRepositoryCustomImpl implements MedTermTempForecastRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(MedTermTempForecastRepositoryCustomImpl.class);

    //DB에 저장 된 값 중 dayNumber에 해당 하는 값들만 얻어온다.
    @Override
    public Optional<MedTermTempForecastJsonResponseDto> findDynamicTempForecastByRegIdAndBaseDateTime(MedTermForecastRequestDto requestDto, String baseDateTime) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MedTermTempForecastJsonResponseDto> query = cb.createQuery(MedTermTempForecastJsonResponseDto.class);
        Root<MedTermTempForecast> root = query.from(MedTermTempForecast.class);

        int dayNumber = requestDto.getDayNumber();
        String regId = requestDto.getRegId();

        query.multiselect(
                root.get("regId"),
                root.get("baseDateTime"),
                root.get("taMax" + dayNumber),
                root.get("taMax" + dayNumber + "High"),
                root.get("taMax" + dayNumber + "Low"),
                root.get("taMin" + dayNumber),
                root.get("taMin" + dayNumber + "High"),
                root.get("taMin" + dayNumber + "Low")

        ).where(
                cb.equal(root.get("regId"), regId),
                cb.equal(root.get("baseDateTime"), baseDateTime)
        );

        List<MedTermTempForecastJsonResponseDto> results = entityManager.createQuery(query).getResultList();
        if(results.isEmpty()){
            LOGGER.error("Retrieved med-term temp data is null");
        }
        LOGGER.info("Retrieved med-term temp Data: {}", results.get(0));
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }
}
