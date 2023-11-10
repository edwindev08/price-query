package com.edwindev08.price.query;


import com.edwindev08.price.builders.PriceAppBuilder;
import com.edwindev08.price.mapper.PriceDtoMapper;
import com.edwindev08.price.model.dto.PriceDto;
import com.edwindev08.price.model.entity.Price;
import com.edwindev08.price.service.PriceByPriorityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceByIdsAndDateHandlerTest {

    @Mock
    private PriceByPriorityService priceByPriorityService;

    @Mock
    private PriceDtoMapper priceDtoMapper;


    private PriceByIdsAndDateHandler priceByIdsAndDateHandler;

    private List<Price> priceList;
    private Long productId;
    private Long brandId;
    private PriceDto priceDto;

    @BeforeEach
    void setUp() {
        priceByIdsAndDateHandler = new PriceByIdsAndDateHandler(priceByPriorityService, priceDtoMapper);

        priceDto = PriceAppBuilder.buildPriceDto();
        productId = 35455L;
        brandId = 1L;
        priceList = List.of(
                PriceAppBuilder.build(),
                PriceAppBuilder.buildWithDateCase1(),
                PriceAppBuilder.buildWithDateCase2(),
                PriceAppBuilder.buildWithDateCase3()
        );
    }

    @Test
    void shouldReturnOnlyTheHighestPriorityPriceDtoFromAList() {

        var startDate = LocalDateTime.of(
                LocalDate.of(2020, 6,14),
                LocalTime.of(10, 0, 0));

        when(priceByPriorityService.getPricesByPriority(startDate, productId, brandId))
                .thenReturn(priceList);
        when(priceDtoMapper.toDto(any(Price.class))).thenReturn(priceDto);

        Optional<PriceDto> result = priceByIdsAndDateHandler.execute(startDate, productId, brandId);

        assertEquals(priceDto, result.orElse(null));
        verify(priceDtoMapper, times(1)).toDto(any(Price.class));


    }

    @Test
    void shouldReturnEmptyIfTheDateIsOutOfRange() {

        var startDate = LocalDateTime.now();

        Optional<PriceDto> result = priceByIdsAndDateHandler.execute(startDate, productId, brandId);

        assertThat(result).isEmpty();
        verify(priceDtoMapper, times(0)).toDto(any(Price.class));

    }

    @Test
    void shouldMapToDtoFromAnPriceEntity() {

        PriceDtoMapper mapper = new PriceDtoMapper();

        Price priceEntity = new Price();
        priceEntity.setId(1L); //Testing setters for Price
        priceEntity.setBrandId(brandId);
        priceEntity.setProductId(productId);
        priceEntity.setPriority(0);
        priceEntity.setFinalPrice(35.50);
        priceEntity.setCurrency("EUR");
        priceEntity.setApplicableRateCode(1);
        priceEntity.setStartDate(priceDto.startDate());
        priceEntity.setEndDate(priceDto.endDate());

        var dto = mapper.toDto(priceEntity);

        assertThat(priceDto).isEqualTo(dto);
    }
}
