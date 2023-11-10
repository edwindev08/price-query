package com.edwindev08.price.mapper;

import com.edwindev08.price.model.dto.PriceDto;
import com.edwindev08.price.model.entity.Price;
import org.springframework.stereotype.Component;

@Component
public class PriceDtoMapper {

    public PriceDto toDto(Price entity) {
        return new PriceDto(
                entity.getProductId(),
                entity.getBrandId(),
                entity.getApplicableRateCode(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getFinalPrice()
                );
    }
}
