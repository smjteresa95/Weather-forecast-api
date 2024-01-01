package com.example.weatherforecast.web.controller;

import com.example.weatherforecast.service.ShortTermForecastService;
import com.example.weatherforecast.service.UltraShortTermForecastService;
import com.example.weatherforecast.web.dto.ShortTermForecastRequestDto;
import com.example.weatherforecast.web.dto.UltraShortTermForecastRequestDto;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;




@RunWith(SpringRunner.class) //Junit4
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.yml")
public class ShortTermForecastControllerTest {

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
    private ShortTermForecastService service;
    @MockBean
    private UltraShortTermForecastService ultraService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @Test
    public void saveUltraShortTermForecastTest() throws Exception {
        UltraShortTermForecastRequestDto requestDto = new UltraShortTermForecastRequestDto(
                "28260515000", "20231231"
        );

        mockMvc.perform(post("/api/weather-forecast/ultra-short-term")
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andDo(document(
                        "/ultra-short-term/save",
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("regionCode").description("지역코드"),
                                fieldWithPath("baseDate").description("기준날짜")
                        ))
                );
    }

    @Test
    public void saveShortTermForecastTest() throws Exception {
        ShortTermForecastRequestDto requestDto = new ShortTermForecastRequestDto(
                "28260515000", "20231231", "0800"
        );

        mockMvc.perform(post("/api/weather-forecast/short-term")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andDo(document(
                        "/short-term/save",
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("regionCode").description("지역코드"),
                                fieldWithPath("baseDate").description("기준날짜"),
                                fieldWithPath("baseTime").description("기준시간")
                        ))
                );
    }

    @Test
    public void getShortTermForecastDetailTest() throws Exception {

        String expectedResponse = "{\"sky\":\"데이터 없음\",\"baseDate\":\"20231231\",\"fcstTime\":\"0900\",\"wsd\":4.00,\"fcstDate\":\"20231231\",\"wav\":0.00,\"pop\":\"30.00\",\"pty\":\"데이터 없음\",\"reh\":null,\"tmn\":null,\"uuu\":1.50,\"sno\":null,\"vec\":338.00,\"tmp\":2.00,\"vvv\":-3.70,\"level1\":\"인천광역시\",\"pcp\":\"0.00\",\"baseTime\":\"0800\",\"level3\":\"검암경서동\",\"tmx\":null,\"level2\":\"서구\"}";

        Mockito.when(service.getShortTermForecastDescription(Mockito.any(ShortTermForecastRequestDto.class)))
                .thenReturn(expectedResponse);


        this.mockMvc.perform(get("/api/weather-forecast/short-term/details")
                        .param("regionCode", "1111053000")
                        .param("baseTime", "20231231")
                        .param("baseDate", "2000")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document(
                        "/short-term/get",
                        preprocessResponse(prettyPrint()),
                        queryParameters(
                                parameterWithName("regionCode").description("지역코드"),
                                parameterWithName("baseDate").description("기준날짜"),
                                parameterWithName("baseTime").description("기준시간")
                        ),
                        responseFields(
                                fieldWithPath("level1").description("시"),
                                fieldWithPath("level2").description("구"),
                                fieldWithPath("level3").description("동/면/읍"),

                                fieldWithPath("baseDate").description("기준일"),
                                fieldWithPath("baseTime").description("기준시간"),
                                fieldWithPath("fcstDate").description("발표일자"),
                                fieldWithPath("fcstTime").description("발표시각"),

                                fieldWithPath("pop").description("강수확률(%)"),
                                fieldWithPath("pty").description("강수형태"),
                                fieldWithPath("pcp").description("1시간 강수량(범주 1 mm)"),
                                fieldWithPath("reh").description("습도(%)"),
                                fieldWithPath("sno").description("1시간 신적설(cm)"),
                                fieldWithPath("sky").description("하늘상태"),
                                fieldWithPath("tmp").description("1시간 기온(℃)"),
                                fieldWithPath("tmn").description("일 최저기온(℃)"),
                                fieldWithPath("tmx").description("일 최고기온(℃)"),
                                fieldWithPath("uuu").description("풍속(동서성분)(m/s)"),
                                fieldWithPath("vvv").description("풍속(남북성분)(m/s)"),
                                fieldWithPath("wav").description("파고(M)"),
                                fieldWithPath("vec").description("풍향(deg)"),
                                fieldWithPath("wsd").description("풍속(m/s)")
                          )
                        )
                );
    }

    @Test
    public void getUltraShortTermForecastDetailTest() throws Exception {

        UltraShortTermForecastRequestDto requestDto = new UltraShortTermForecastRequestDto(
                "28260515000", "20231231"
        );
        String fcstTime = "1300";

        String expectedResponse =  "{\"sky\":\"데이터 없음\",\"baseDate\":\"20231231\",\"fcstTime\":\"1300\",\"wsd\":3.00,\"t1h\":4.00,\"fcstDate\":\"20231231\",\"reh\":80.00,\"uuu\":2.30,\"pty\":\"데이터 없음\",\"vec\":297.00,\"vvv\":-1.10,\"lgt\":0,\"level1\":\"인천광역시\",\"pcp\":\"강수없음\",\"baseTime\":\"1230\",\"level3\":\"검암경서동\",\"level2\":\"서구\"}";

        Mockito.when(ultraService.generateJsonDataForGivenFcstTime(
                Mockito.any(UltraShortTermForecastRequestDto.class),
                Mockito.anyString())
        ).thenReturn(expectedResponse);

        this.mockMvc.perform(get("/api/weather-forecast/ultra-short-term/details")
                        .param("regionCode", requestDto.getRegionCode())
                        .param("baseDate", requestDto.getBaseDate())
                        .param("fcstTime", fcstTime)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(document(
                        "/ultra-short-term/get",
                        preprocessResponse(prettyPrint()),
                        queryParameters(
                                parameterWithName("regionCode").description("지역코드"),
                                parameterWithName("baseDate").description("기준 날짜"),
                                parameterWithName("fcstTime").description("기준시간; HHMM형태로 한시간 단위")
                        ),
                        responseFields(
                                fieldWithPath("level1").description("시"),
                                fieldWithPath("level2").description("구"),
                                fieldWithPath("level3").description("동/면/읍"),

                                fieldWithPath("baseDate").description("기준일"),
                                fieldWithPath("baseTime").description("기준시간"),
                                fieldWithPath("fcstDate").description("발표일자"),
                                fieldWithPath("fcstTime").description("발표시각"),

                                fieldWithPath("pcp").description("1시간 강수량(mm)"),
                                fieldWithPath("sky").description("하늘상태"),
                                fieldWithPath("reh").description("습도(%)"),
                                fieldWithPath("uuu").description("풍속(동서성분)(m/s)"),
                                fieldWithPath("vvv").description("풍속(남북성분)(m/s)"),
                                fieldWithPath("vec").description("풍향 (deg)"),
                                fieldWithPath("wsd").description("풍속(m/s)"),
                                fieldWithPath("t1h").description("기온"),
                                fieldWithPath("pty").description("강수형태"),
                                fieldWithPath("lgt").description("낙뢰")
                        )
                ));
        }
    }
