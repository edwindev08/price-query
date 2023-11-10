package com.edwindev08.price.rest.controller;

import com.edwindev08.price.builders.PriceDboBuilder;
import com.edwindev08.price.model.dto.PriceDto;
import com.edwindev08.price.model.exception.PriceNotFoundException;
import com.edwindev08.price.query.PriceByIdsAndDateHandler;
import com.edwindev08.price.rest.controller.PriceQueryController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceQueryControllerTest {

    private PriceQueryController priceQueryController;
    @Mock
    private PriceByIdsAndDateHandler priceByIdsAndDateHandler;

    private List<PriceDto> expectedPrice;
    private Long productId;
    private Long brandId;

    @BeforeEach
    void setUp() {

        priceQueryController = new PriceQueryController(priceByIdsAndDateHandler);

        productId = 35455L;
        brandId = 1L;
        expectedPrice = List.of(
                PriceDboBuilder.build(),
                PriceDboBuilder.buildWithDateCase1(),
                PriceDboBuilder.buildWithDateCase2(),
                PriceDboBuilder.buildWithDateCase3()
        );

    }

    @Test
    void shouldReturnPriceWithApplicableRateCode1() {

        var startDate = LocalDateTime.of(
                LocalDate.of(2020, 6,14),
                LocalTime.of(10, 0, 0));

        when(priceByIdsAndDateHandler.execute(startDate, productId, brandId))
                .thenReturn(Optional.ofNullable(expectedPrice.get(0)));

        var result = priceQueryController.getPriceByIdsAndDate(startDate, productId, brandId);

        var body = result.getBody();

        assertNotNull(body);
        assertThat(expectedPrice.get(0)).isEqualTo(body);
        assertThat(35.50).isEqualTo(body.price());
        assertThat(body.applicableRateCode()).isEqualTo(1);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);


    }

    @Test
    void shouldReturnPriceWithApplicableRateCode2() {

        var startDate = LocalDateTime.of(
                LocalDate.of(2020, 6,14),
                LocalTime.of(16, 0, 0));

        when(priceByIdsAndDateHandler.execute(startDate, productId, brandId))
                .thenReturn(Optional.ofNullable(expectedPrice.get(1)));

        var result = priceQueryController.getPriceByIdsAndDate(startDate, productId, brandId);

        var body = result.getBody();

        assertNotNull(body);
        assertThat(expectedPrice.get(1)).isEqualTo(body);
        assertThat(25.45).isEqualTo(body.price());
        assertThat(body.applicableRateCode()).isEqualTo(1);


    }

    @Test
    void returnPriceNotFoundExceptionWhenPriceNotExists() {

        var badDate = LocalDateTime.now();

        assertThatThrownBy(() -> priceQueryController.getPriceByIdsAndDate(badDate, productId, brandId))
                .isInstanceOf(PriceNotFoundException.class);

    }

}
