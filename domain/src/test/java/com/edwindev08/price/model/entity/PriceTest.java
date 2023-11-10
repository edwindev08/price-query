package com.edwindev08.price.model.entity;

import com.edwindev08.price.builders.PriceDomainBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceTest {

    @Test
    void shouldCreateAPriceEntityByAllArgsConstructorTest() {
        var startDate = LocalDateTime.of(
                LocalDate.of(2020, 6,14),
                LocalTime.of(0, 0, 0));
        var endDate = LocalDateTime.of(
                LocalDate.of(2020, 12,31),
                LocalTime.of(23, 59, 59));

        var price = PriceDomainBuilder.build();


        assertEquals(1L, price.getId());
        assertEquals(1L, price.getBrandId());
        assertEquals(startDate, price.getStartDate());
        assertEquals(endDate, price.getEndDate());
        assertEquals(1, price.getApplicableRateCode());
        assertEquals(35455L, price.getProductId());
        assertEquals(0, price.getPriority());
        assertEquals(35.50, price.getFinalPrice());
        assertEquals("EUR", price.getCurrency());


    }

}
