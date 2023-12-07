package com.edwindev08.price.adapter.jpa.dao;

import com.edwindev08.price.adapter.entity.PriceEntity;
import com.edwindev08.price.adapter.jpa.repository.PriceJpaAdapterRepository;
import com.edwindev08.price.adapter.mapper.PriceDboMapper;
import com.edwindev08.price.builders.PriceDboBuilder;
import com.edwindev08.price.model.entity.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceH2DaoTest {

    @Mock
    private PriceJpaAdapterRepository priceJpaAdapterRepository;

    @Mock
    private PriceDboMapper priceDboMapper;
    private PriceH2Dao priceH2Dao;
    private List<PriceEntity> priceEntity;
    LocalDateTime startDate;
    Long productId;
    Long brandId;

    @BeforeEach
    void setUp() {
        priceH2Dao = new PriceH2Dao(priceJpaAdapterRepository, priceDboMapper);
        priceEntity = List.of(
                PriceDboBuilder.buildEntity());
        productId = 35455L;
        brandId = 1L;
        startDate = priceEntity.get(0).getStartDate();
    }
    @Test
    void testGetPricesByStartDateProductIdAndBrandId() {
        Price priceDomain = PriceDboBuilder.buildDomain();
        when(priceJpaAdapterRepository
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(startDate, startDate, productId, brandId))
                .thenReturn(priceEntity);
        when(priceDboMapper.toDomain(priceEntity.get(0))).thenReturn(priceDomain);

        var price = priceH2Dao.getPricesByStartDateProductIdAndBrandId(startDate,productId, brandId);

        assertThat(priceDomain).isEqualTo(price.get(0));

    }
}

