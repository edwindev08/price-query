package com.edwindev08.price.model.dto;

import java.time.LocalDateTime;

public record PriceDto(
        Long productId,
        Long brandId,
        Integer applicableRateCode,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Double price
) {

}
