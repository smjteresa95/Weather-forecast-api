package com.example.weatherforecast.web.controller;

import com.example.weatherforecast.api.ShortTermForecastApiClient;
import com.example.weatherforecast.web.controller.ShortTermForecastApiController;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ShortTermForecastApiController.class)
@AutoConfigureMockMvc
public class ShortTermForecastControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ShortTermForecastApiClient apiClient;
//
//    @Test
//    @DisplayName("Open Api 통신 테스트")
//    public void callOpenApi() throws Exception {
//        Long regionCode = 1171057000L;
//        String baseTime = "1100";
//        String baseDate = "20230525";
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//
//        params.add("base_time", baseTime);
//        params.add("base_date", baseDate);
//
//        ShortTermForecastRequestDto expectedRequest = new ShortTermForecastRequestDto(regionCode, baseDate, baseTime, 0, 0, 0, 0);
//
//        // Mock 객체를 사용하여 apiClient.callShortTermForecastApi 호출을 설정
//        when(apiClient.callShortTermForecastApi(eq("shortTerm"), eq(expectedRequest)))
//                .thenReturn("Mocked API Response");
//
//        this.mvc.perform(
//                get("/api/short-term/forecast")
//                        .params(params))
//                .andExpect(status().isOk());
//    }
}
