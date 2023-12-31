package com.example.weatherforecast.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter @Setter
@Configuration
@Component
public class ApiConfig {
    @Value("${spring.api.short-term-key}")
    private String shortTermKey;

    @Value("${spring.api.short-term-endpoint}")
    private String shortTermEndpoint;

    @Value("${spring.api.med-term-key}")
    private String medTermKey;

    @Value("${spring.api.med-term-land-endpoint}")
    private String medTermLandEndpoint;

    @Value("${spring.api.med-term-temp-endpoint}")
    private String medTermTempEndpoint;

    @Value("${spring.api.ultra-short-term-endpoint}")
    private String ultraShortTermEndpoint;

    @Value("${spring.api.data-type}")
    private String dataType;
}
