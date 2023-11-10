package com.edwindev08.price.integrationTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URL = "/price";
    private static final String PRODUCT_ID = "35455";
    private static final String BRAND_ID = "1";

    @Test
    void TestCase1ShouldApplyRateCode1() throws Exception {

       mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .queryParam("startDate", "2020-06-14-10.00.00")
                    .queryParam("productId", PRODUCT_ID)
                    .queryParam("brandId", BRAND_ID))
                .andExpect(status().isOk())
               .andExpect(jsonPath("$.price").value(35.50))
               .andExpect(jsonPath("$.applicableRateCode").value(1));

    }

    @Test
    void TestCase2ShouldApplyRateCode2() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("startDate", "2020-06-14-16.00.00")
                        .queryParam("productId", PRODUCT_ID)
                        .queryParam("brandId", BRAND_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.applicableRateCode").value(2));

    }

    @Test
    void TestCase3ShouldApplyRateCode1() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("startDate", "2020-06-14-21.00.00")
                        .queryParam("productId", PRODUCT_ID)
                        .queryParam("brandId", BRAND_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.applicableRateCode").value(1));

    }

    @Test
    void TestCase4ShouldApplyRateCode3() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("startDate", "2020-06-15-10.00.00")
                        .queryParam("productId", PRODUCT_ID)
                        .queryParam("brandId", BRAND_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30.50))
                .andExpect(jsonPath("$.applicableRateCode").value(3));

    }

    @Test
    void TestCase5ShouldApplyRateCode4() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("startDate", "2020-06-16-21.00.00")
                        .queryParam("productId", PRODUCT_ID)
                        .queryParam("brandId", BRAND_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.applicableRateCode").value(4));

    }

}
