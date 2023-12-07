package com.edwindev08.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import org.springframework.http.HttpHeaders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class SharedTest {

    @Autowired
    private MockMvc mockMvc;

    protected void assertResponse(
            String endpoint,
            String startDate,
            String productId,
            String brandId,
            Integer expectedStatusCode,
            Double expectedPrice,
            Integer expectedApplicableRateCode) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(get(endpoint)
                        .headers(headers)
                        .queryParam("startDate", startDate)
                        .queryParam("productId", productId)
                        .queryParam("brandId", brandId))
                .andExpect(status().is(expectedStatusCode))
                .andExpect(jsonPath("$.price").value(expectedPrice))
                .andExpect(jsonPath("$.applicableRateCode").value(expectedApplicableRateCode));
    }

    protected void assertException(String endpoint,
                                  String startDate,
                                  String productId,
                                  String brandId,
                                  Integer expectedStatusCode) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(get(endpoint)
                        .headers(headers)
                        .queryParam("startDate", startDate)
                        .queryParam("productId", productId)
                        .queryParam("brandId", brandId))
                .andExpect(status().is(expectedStatusCode));

    }

}
