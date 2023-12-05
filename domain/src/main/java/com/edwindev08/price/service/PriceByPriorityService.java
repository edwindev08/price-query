package com.edwindev08.price.service;

import com.edwindev08.price.model.entity.Price;
import com.edwindev08.price.model.exception.PriceNotFoundException;
import com.edwindev08.price.port.dao.PriceDao;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PriceByPriorityService {

    private final PriceDao priceDao;

    public PriceByPriorityService(PriceDao priceDao) {
        this.priceDao = priceDao;
    }

    public List<Price> getPricesByPriority(
            LocalDateTime startDate, Long productId, Long brandId) {

        return priceDao.getPricesByStartDateProductIdAndBrandId(startDate, productId, brandId);

    }
}
