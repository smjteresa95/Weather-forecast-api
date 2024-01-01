package com.example.weatherforecast.domain.medtermforecast;

import com.example.weatherforecast.web.dto.MedTermForecastRequestDto;
import com.example.weatherforecast.web.dto.MedTermLandForecastAfterAWeekJsonResponseDto;
import com.example.weatherforecast.web.dto.MedTermLandForecastWithinAWeekJsonResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MedTermLandForecastRepositoryCustomImpl implements MedTermLandForecastRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(MedTermLandForecastRepositoryCustomImpl.class);

    //DB에 저장 된 값 중 7일 이전 날씨 얻어온다.
    @Override
    @Transactional
    public Optional<MedTermLandForecastWithinAWeekJsonResponseDto> findDynamicForecastWithinAWeekByRegIdAndBaseDate(MedTermForecastRequestDto requestDto,
                                                                                                                    String baseDateTime) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<MedTermLandForecast> root = query.from(MedTermLandForecast.class);

        int dayNumber = requestDto.getDayNumber();
        String regId = requestDto.getRegId();

        if (dayNumber > 7 || dayNumber < 3) {
            LOGGER.error("dayNumber range should be between 3-7 day.");
            return Optional.empty();
        }

        query.multiselect(
                root.get("regId"),
                root.get("baseDateTime"),
                root.get("rnSt" + dayNumber + "Am"),
                root.get("rnSt" + dayNumber + "Pm"),
                root.get("wf" + dayNumber + "Am"),
                root.get("wf" + dayNumber + "Pm")
        ).where(
                cb.equal(root.get("regId"), regId),
                cb.equal(root.get("baseDateTime"), baseDateTime)
        );

        try {
            Object[] result = entityManager.createQuery(query).getSingleResult();
            MedTermLandForecastWithinAWeekJsonResponseDto responseDto = new MedTermLandForecastWithinAWeekJsonResponseDto(
                    (String) result[0],
                    (String) result[1],
                    (Integer) result[2],
                    (Integer) result[3],
                    (String) result[4],
                    (String) result[5]
            );
            return Optional.of(responseDto);
        } catch (NoResultException e) {
            LOGGER.error("No data found for the given criteria", e);
            return Optional.empty();
        }
    }

    //DB에 저장 된 값 중 7일 이후 날씨 얻어온다.
    @Override
    @Transactional
    public Optional<MedTermLandForecastAfterAWeekJsonResponseDto> findDynamicForecastAfterAWeekByRegIdAndBaseDate(MedTermForecastRequestDto requestDto,
                                                                                                                  String baseDateTime) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MedTermLandForecastAfterAWeekJsonResponseDto> query = cb.createQuery(MedTermLandForecastAfterAWeekJsonResponseDto.class);
        Root<MedTermLandForecast> root = query.from(MedTermLandForecast.class);

        int dayNumber = requestDto.getDayNumber();
        String regId = requestDto.getRegId();

        if (dayNumber <= 7){
            LOGGER.error("dayNumber range should be between 8-10 day.");
            return Optional.empty();
        }

        query.multiselect(
                root.get("regId"),
                root.get("baseDateTime"),
                root.get("rnSt" + dayNumber),
                root.get("wf" + dayNumber)
        ).where(
                cb.equal(root.get("regId"), regId),
                cb.equal(root.get("baseDateTime"), baseDateTime)
        );

        List<MedTermLandForecastAfterAWeekJsonResponseDto> results = entityManager.createQuery(query).getResultList();
        if(results.isEmpty()){
            LOGGER.error("Retrieved data is null");
        }
        LOGGER.info("Retrieved Data: {}", results.get(0));
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }



}
