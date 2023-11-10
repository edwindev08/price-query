package com.edwindev08.price.builders;



import com.edwindev08.price.adapter.entity.PriceEntity;
import com.edwindev08.price.model.dto.PriceDto;
import com.edwindev08.price.model.entity.Price;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PriceDboBuilder {
    public static PriceDto build() {
        var childObject = new ChildObjectBuilder();
        return new PriceDto(
                childObject.buildProduct(),
                childObject.buildBrandId(),
                childObject.buildApplicableRateCode(),
                childObject.buildStartDate(),
                childObject.buildEndDate(),
                childObject.buildFinalPrice()
        );
    }

    public static PriceDto buildWithDateCase1() {

        var startDate = LocalDateTime.of(LocalDate.of(2020, 6,14),
                LocalTime.of(15, 0, 0));
        var endDate = LocalDateTime.of(LocalDate.of(2020, 6,14),
                LocalTime.of(18, 30, 0));

        var childObject = new ChildObjectBuilder();
        return new PriceDto(
                childObject.buildProduct(),
                childObject.buildBrandId(),
                childObject.buildApplicableRateCode(),
                startDate,
                endDate,
                25.45
        );
    }

    public static PriceDto buildWithDateCase2() {

        var startDate = LocalDateTime.of(LocalDate.of(2020, 6,15),
                LocalTime.of(0, 0, 0));
        var endDate = LocalDateTime.of(LocalDate.of(2020, 6,15),
                LocalTime.of(11, 0, 0));

        var childObject = new ChildObjectBuilder();
        return new PriceDto(
                childObject.buildProduct(),
                childObject.buildBrandId(),
                childObject.buildApplicableRateCode(),
                startDate,
                endDate,
                30.50
        );
    }

    public static PriceDto buildWithDateCase3() {

        var startDate = LocalDateTime.of(LocalDate.of(2020, 6,15),
                LocalTime.of(16, 0, 0));
        var endDate = LocalDateTime.of(LocalDate.of(2020, 12,31),
                LocalTime.of(23, 59, 59));

        var childObject = new ChildObjectBuilder();
        return new PriceDto(
                childObject.buildProduct(),
                childObject.buildBrandId(),
                childObject.buildApplicableRateCode(),
                startDate,
                endDate,
                38.95
        );
    }

    public static PriceDto buildPriceDto() {
        var childObject = new ChildObjectBuilder();
        return new PriceDto(
                childObject.buildProduct(),
                childObject.buildBrandId(),
                childObject.buildApplicableRateCode(),
                childObject.buildStartDate(),
                childObject.buildEndDate(),
                childObject.buildFinalPrice()
        );
    }

    public static Price buildDomain() {
        var childObject = new ChildObjectBuilder();
        return new Price(
                null,
                childObject.buildBrandId(),
                childObject.buildStartDate(),
                childObject.buildEndDate(),
                childObject.buildApplicableRateCode(),
                childObject.buildProduct(),
                childObject.buildPriority(),
                childObject.buildFinalPrice(),
                childObject.buildCurrency()
        );
    }
}
