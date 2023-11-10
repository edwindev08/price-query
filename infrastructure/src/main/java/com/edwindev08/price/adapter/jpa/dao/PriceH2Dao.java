package com.edwindev08.price.adapter.jpa.dao;

import com.edwindev08.price.adapter.jpa.repository.PriceJpaAdapterRepository;
import com.edwindev08.price.adapter.mapper.PriceDboMapper;
import com.edwindev08.price.model.entity.Price;
import com.edwindev08.price.port.dao.PriceDao;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PriceH2Dao implements PriceDao {

    private final PriceJpaAdapterRepository priceJpaAdapterRepository;
    private final PriceDboMapper priceDboMapper;

    public PriceH2Dao(PriceJpaAdapterRepository priceJpaAdapterRepository, PriceDboMapper priceDboMapper) {
        this.priceJpaAdapterRepository = priceJpaAdapterRepository;
        this.priceDboMapper = priceDboMapper;
    }

    @Override
    public List<Price> getPricesByStartDateProductIdAndBrandId(
            LocalDateTime startDate, Long productId, Long brandId)
    {
        return priceJpaAdapterRepository.findByStartDateLessThanEqualAndProductIdAndBrandId(
                startDate, productId, brandId)
                .stream()
                .map(priceDboMapper::toDomain)
                .toList();
    }
}
