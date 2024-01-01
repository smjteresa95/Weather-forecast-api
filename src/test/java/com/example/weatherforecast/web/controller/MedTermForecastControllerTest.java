package com.example.weatherforecast.web.controller;

import com.example.weatherforecast.service.MedTermLandForecastService;
import com.example.weatherforecast.service.MedTermTempForecastService;
import com.example.weatherforecast.web.dto.MedTermForecastRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) //Junit4
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.yml")
public class MedTermForecastControllerTest {

    @LocalServerPort
    protected int port;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;
    protected MockMvc mockMvc;

    @MockBean
    private MedTermTempForecastService tempService;
    @MockBean
    private MedTermLandForecastService landService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @Test
    public void saveMedTermLandForecastTest() throws Exception {

        String regId = "11B00000";
        mockMvc.perform(post("/api/weather-forecast/med-term/land")
                        .param("regId", regId))
                .andExpect(status().isOk())
                .andDo(document("/med-term/land-forecast/save"));
    }

    @Test
    public void saveMedTermTempForecastTest() throws Exception {

        String regId = "11D20501";
        mockMvc.perform(post("/api/weather-forecast/med-term/temp")
                        .param("regId", regId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document(
                        "/med-term/temp-forecast/save")
                );
    }

    @Test
    public void getMedTermTempForecastTest() throws Exception {
        MedTermForecastRequestDto requestDto = new MedTermForecastRequestDto(
                "11D20501", 4
        );
        String expectedResponse = "{\"baseDate\":\"20240101\",\"taMaxLow\":1,\"taMinHigh\":2,\"dayNumber\":4,\"taMax\":10,\"taMin\":4,\"taMinLow\":1,\"region\":\"강릉\",\"baseTime\":\"0600\",\"taMaxHigh\":2}";

        Mockito.when(tempService.getMedTermTempForecastByDayNumber(Mockito.any(MedTermForecastRequestDto.class)))
                .thenReturn(expectedResponse);

        this.mockMvc.perform(get("/api/weather-forecast/med-term/temp/details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(document(
                        "/med-term/temp-forecast/get",
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("regId").description("지역코드"),
                                fieldWithPath("dayNumber").description("해당 날짜 후")
                        ),
                        responseFields(
                                fieldWithPath("region").description("예보구역"),
                                fieldWithPath("baseDate").description("예보날짜"),
                                fieldWithPath("baseTime").description("예보시간"),
                                fieldWithPath("dayNumber").description("해당 날짜 후"),

                                fieldWithPath("taMax").description("선택일 후 예상최저기온(℃)"),
                                fieldWithPath("taMaxHigh").description("선택일 후 예상최저기온 하한 범위"),
                                fieldWithPath("taMaxLow").description("선택일 후 예상최저기온 상한 범위"),
                                fieldWithPath("taMin").description("선택일 후 예상최고기온(℃)"),
                                fieldWithPath("taMinHigh").description("선택일 후 예상최고기온 하한 범위"),
                                fieldWithPath("taMinLow").description("선택일 후 예상최고기온 상한 범위")
                        )
                ));

    }

    @Test
    public void getMedTermLandAfterAWeekForecastTest() throws Exception {
        MedTermForecastRequestDto requestDto = new MedTermForecastRequestDto(
                "11B00000", 9
        );

        String expectedResponse = "{\"baseDate\":\"20240101\",\"dayNumber\":9,\"rnSt\":10,\"region\":\"서울, 인천, 경기도\",\"baseTime\":\"0600\",\"wf\":\"맑음\"}";

        Mockito.when(landService.getMedTermForecastAfterDayNumber(Mockito.any(MedTermForecastRequestDto.class)))
                .thenReturn(expectedResponse);

        this.mockMvc.perform(get("/api/weather-forecast/med-term/land/details")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto))
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andDo(document(
                        "/med-term/land-forecast/get/after-a-week",
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("regId").description("지역코드"),
                                fieldWithPath("dayNumber").description("해당 날짜 후")
                        ),
                        responseFields(
                                fieldWithPath("region").description("예보지역"),
                                fieldWithPath("baseDate").description("기준날짜"),
                                fieldWithPath("baseTime").description("기준시간"),
                                fieldWithPath("dayNumber").description("해당 날짜 후"),
                                fieldWithPath("rnSt").description("선택일 후 강수확률"),
                                fieldWithPath("wf").description("선택일 후 날씨예보")
                        ))
                );
    }

    @Test
    public void getMedTermLandWithinAWeekForecastTest() throws Exception {
        MedTermForecastRequestDto requestDto = new MedTermForecastRequestDto(
                "11B00000", 4
        );

        String expectedResponse = "{\"baseDate\":\"20240101\",\"rnStPm\":0,\"rnStAm\":20,\"dayNumber\":4,\"wfPm\":\"맑음\",\"region\":\"서울, 인천, 경기도\",\"wfAm\":\"맑음\",\"baseTime\":\"0600\"}";

        Mockito.when(landService.getMedTermForecastAfterDayNumber(Mockito.any(MedTermForecastRequestDto.class)))
                .thenReturn(expectedResponse);

        this.mockMvc.perform(get("/api/weather-forecast/med-term/land/details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(document(
                                "/med-term/land-forecast/get/within-a-week",
                        preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("regId").description("지역코드"),
                                        fieldWithPath("dayNumber").description("해당 날짜 후")
                                ),
                                responseFields(
                                        fieldWithPath("region").description("예보지역"),
                                        fieldWithPath("baseDate").description("기준날짜"),
                                        fieldWithPath("baseTime").description("기준시간"),
                                        fieldWithPath("dayNumber").description("해당 날짜 후"),
                                fieldWithPath("rnStAm").description("선택일 후 오전강수확률"),
                                fieldWithPath("rnStPm").description("선택일 후 오후강수확률"),
                                fieldWithPath("wfAm").description("선택일 후 오전날씨예보"),
                                fieldWithPath("wfPm").description("선택일 후 오후날씨예보")
                                ))
                );
    }




}
