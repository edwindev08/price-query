package com.edwindev08.price.service;


import com.edwindev08.price.builders.PriceDomainBuilder;
import com.edwindev08.price.model.entity.Price;
import com.edwindev08.price.model.exception.PriceNotFoundException;
import com.edwindev08.price.port.dao.PriceDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceByPriorityServiceTest {

    private PriceByPriorityService priceByPriorityService;
    @Mock
    private PriceDao priceDao;

    private List<Price> expectedPrice;
    private Long productId;
    private Long brandId;

    @BeforeEach
    void setUp() {

        priceByPriorityService = new PriceByPriorityService(priceDao);

        productId = 1L;
        brandId = 1L;
        expectedPrice = List.of(
                PriceDomainBuilder.build(),
                PriceDomainBuilder.buildWithDateCase1(),
                PriceDomainBuilder.buildWithDateCase2(),
                PriceDomainBuilder.buildWithDateCase3()
        );

    }

    @Test
    void shouldReturnPriceWithApplicableRateCode1() {
        var startDate = LocalDateTime.of(
                LocalDate.of(2020, 6,14),
                LocalTime.of(10, 0, 0));

        when(priceDao.getPricesByStartDateProductIdAndBrandId(startDate, productId, brandId))
                .thenReturn(expectedPrice);

        List<Price> result = priceByPriorityService.getPricesByPriority(startDate, productId, brandId);


        assertNotNull(result);
        assertThat(expectedPrice.get(0)).isEqualTo(result.get(0));
        assertThat(expectedPrice.get(0).getFinalPrice()).isEqualTo(result.get(0).getFinalPrice());
        assertThat(result.get(0).getApplicableRateCode()).isEqualTo(1);


    }

    @Test
    void returnNotFoundWhenPriceNotExists() {

        var badDate = LocalDateTime.now();

        assertThatThrownBy(() -> priceByPriorityService.getPricesByPriority(badDate, productId, brandId))
                .isInstanceOf(PriceNotFoundException.class);

    }

}
