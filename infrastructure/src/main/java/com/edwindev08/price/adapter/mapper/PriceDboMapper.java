package com.edwindev08.price.adapter.mapper;

import com.edwindev08.price.adapter.entity.PriceEntity;
import com.edwindev08.price.model.entity.Price;
import org.springframework.stereotype.Component;

@Component
public class PriceDboMapper {

    public Price toDomain(PriceEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Price(
                entity.getId(),
                entity.getBrandId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPriceList(),
                entity.getProductId(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurrency()
        );
    }

}
