package com.edwindev08.price.integrationTest;

import com.edwindev08.price.SharedTest;
import org.junit.jupiter.api.Test;


class PriceQueryControllerTest extends SharedTest {

    private static final String BASE_URL = "/price";
    private static final String PRODUCT_ID = "35455";
    private static final String BRAND_ID = "1";
    private static final Integer OK = 200;
    private static final Integer NOT_FOUND = 404;
    private static final Integer BAD_REQUEST = 400;



    @Test
    void TestCase1ShouldApplyRateCode1() throws Exception {

       assertResponse(BASE_URL,
               "2020-06-14-10.00.00",
               PRODUCT_ID,
               BRAND_ID,
               200,
               35.50,
               1
       );

    }

    @Test
    void TestCase2ShouldApplyRateCode2() throws Exception {

        assertResponse(BASE_URL,
                "2020-06-14-16.00.00",
                PRODUCT_ID,
                BRAND_ID,
                OK,
                25.45,
                2
        );

    }

    @Test
    void TestCase3ShouldApplyRateCode1() throws Exception {

        assertResponse(BASE_URL,
                "2020-06-14-21.00.00",
                PRODUCT_ID,
                BRAND_ID,
                OK,
                35.50,
                1
        );

    }

    @Test
    void TestCase4ShouldApplyRateCode3() throws Exception {

        assertResponse(BASE_URL,
                "2020-06-15-10.00.00",
                PRODUCT_ID,
                BRAND_ID,
                OK,
                30.50,
                3
        );

    }

    @Test
    void TestCase5ShouldApplyRateCode4() throws Exception {

        assertResponse(BASE_URL,
                "2020-06-16-21.00.00",
                PRODUCT_ID,
                BRAND_ID,
                OK,
                38.95,
                4
        );

    }

    @Test
    void shouldReturn404WhenPriceDoesntExists() throws Exception {

        assertException(BASE_URL,
                "2023-06-16-21.00.00",
                PRODUCT_ID,
                BRAND_ID,
                NOT_FOUND
        );
    }

    @Test
    void shouldReturn400WhenBadParamAreSent() throws Exception {

        assertException(BASE_URL,
                "2023-06-16-2",
                PRODUCT_ID,
                BRAND_ID,
                BAD_REQUEST
        );
    }

}
