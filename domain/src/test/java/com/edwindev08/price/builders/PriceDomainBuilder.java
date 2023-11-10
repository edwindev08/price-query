package com.edwindev08.price.builders;

import com.edwindev08.price.model.entity.Price;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PriceDomainBuilder {
    public static Price build() {
        var childObject = new ChildObjectBuilder();
        return new Price(
                1L,
                childObject.buildBrandId(),
                childObject.buildStartDate(),
                childObject.buildEndDate(),
                childObject.buildPriceList(),
                childObject.buildProduct(),
                childObject.buildPriority(),
                childObject.buildFinalPrice(),
                childObject.buildCurrency()
        );
    }

    public static Price buildWithDateCase1() {

        var startDate = LocalDateTime.of(LocalDate.of(2020, 6,14),
                LocalTime.of(15, 0, 0));
        var endDate = LocalDateTime.of(LocalDate.of(2020, 6,14),
                LocalTime.of(18, 30, 0));

        var childObject = new ChildObjectBuilder();
        return new Price(
                2L,
                childObject.buildBrandId(),
                startDate,
                endDate,
                childObject.buildPriceList(),
                childObject.buildProduct(),
                childObject.buildPriority(),
                25.45,
                childObject.buildCurrency()
        );
    }

    public static Price buildWithDateCase2() {

        var startDate = LocalDateTime.of(LocalDate.of(2020, 6,15),
                LocalTime.of(0, 0, 0));
        var endDate = LocalDateTime.of(LocalDate.of(2020, 6,15),
                LocalTime.of(11, 0, 0));

        var childObject = new ChildObjectBuilder();
        return new Price(
                2L,
                childObject.buildBrandId(),
                startDate,
                endDate,
                childObject.buildPriceList(),
                childObject.buildProduct(),
                childObject.buildPriority(),
                25.45,
                childObject.buildCurrency()
        );
    }

    public static Price buildWithDateCase3() {

        var startDate = LocalDateTime.of(LocalDate.of(2020, 6,15),
                LocalTime.of(16, 0, 0));
        var endDate = LocalDateTime.of(LocalDate.of(2020, 12,31),
                LocalTime.of(23, 59, 59));

        var childObject = new ChildObjectBuilder();
        return new Price(
                2L,
                childObject.buildBrandId(),
                startDate,
                endDate,
                childObject.buildPriceList(),
                childObject.buildProduct(),
                childObject.buildPriority(),
                25.45,
                childObject.buildCurrency()
        );
    }
}
