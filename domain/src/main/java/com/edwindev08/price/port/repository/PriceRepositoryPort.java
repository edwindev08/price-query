package com.edwindev08.price.port.repository;

import com.edwindev08.price.model.entity.Price;


public interface PriceRepositoryPort {

    Price create(Price request);
    void deleteById(Long id);
    Price update(Price request);

}
