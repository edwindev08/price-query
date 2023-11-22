package com.edwindev08.price.adapter.entity;

import com.edwindev08.price.builders.PriceDboBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PriceEntityTest {

    @Test
    void shouldCreateAPriceEntityByAllArgsConstructorTest() {
        var startDate = LocalDateTime.of(
                LocalDate.of(2020, 6,14),
                LocalTime.of(0, 0, 0));
        var endDate = LocalDateTime.of(
                LocalDate.of(2020, 12,31),
                LocalTime.of(23, 59, 59));

        var price = PriceDboBuilder.buildEntity();
        var price2 = PriceDboBuilder.buildEntityCase1();


        assertEquals(1L, price.getBrandId());
        assertEquals(startDate, price.getStartDate());
        assertEquals(endDate, price.getEndDate());
        assertEquals(1, price.getPriceList());
        assertEquals(35455L, price.getProductId());
        assertEquals(0, price.getPriority());
        assertEquals(35.50, price.getPrice());
        assertEquals("EUR", price.getCurrency());
        assertNotEquals(price.hashCode(), price2.hashCode());
        assertNotEquals(price, price2);


    }

}