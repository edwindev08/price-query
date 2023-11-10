package com.edwindev08.price.builders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ChildObjectBuilder {

    private Long brandId = 1L;
    private LocalDateTime startDate = LocalDateTime.of(
            LocalDate.of(2020, 6,14),
            LocalTime.of(0, 0, 0));
    private LocalDateTime endDate = LocalDateTime.of(
            LocalDate.of(2020, 12,31),
            LocalTime.of(23, 59, 59));
    private Integer priceList = 1;
    private Long productId = 35455L;
    private Integer priority = 0;
    private Double finalPrice = 35.50;
    private String currency = "EUR";

    public Long buildBrandId() {
        return this.brandId;
    }
    public LocalDateTime buildStartDate() {
        return this.startDate;
    }
    public LocalDateTime buildEndDate() {
        return this.endDate;
    }
    public Integer buildPriceList() {
        return this.priceList;
    }
    public Long buildProduct() {
        return this.productId;
    }
    public Integer buildPriority() {
        return this.priority;
    }
    public Double buildFinalPrice() {
        return this.finalPrice;
    }
    public String buildCurrency() {
        return this.currency;
    }

}
