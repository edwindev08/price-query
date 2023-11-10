package com.edwindev08.price.adapter.mapper;

import com.edwindev08.price.adapter.entity.PriceEntity;
import com.edwindev08.price.builders.PriceDboBuilder;
import com.edwindev08.price.model.entity.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class PriceDboMapperTest {

    private PriceDboMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new PriceDboMapper();
    }

    private LocalDateTime startDate = LocalDateTime.of(
            LocalDate.of(2020, 6,14),
            LocalTime.of(0, 0, 0));
    private LocalDateTime endDate = LocalDateTime.of(
            LocalDate.of(2020, 12,31),
            LocalTime.of(23, 59, 59));


    @Test
    void shouldMapToDtoFromAnPriceEntity() {

        Price priceDomain = PriceDboBuilder.buildDomain();

        PriceDboMapper mapper = new PriceDboMapper();

        PriceEntity priceEntity = new PriceEntity(
                1L,
                startDate,
                endDate,
                1,
                35455L,
                0,
                35.50,
                "EUR");

        var domain = mapper.toDomain(priceEntity);

        assertThat(priceDomain).isEqualTo(domain);
    }

    @Test
    void shouldReturnNullWhenPriceEntityIsNUll() {

        var domain = mapper.toDomain(null);

        assertNull(domain);
    }
}
