package com.edwindev08.price.config;

import com.edwindev08.price.port.dao.PriceDao;
import com.edwindev08.price.service.PriceByPriorityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriceBeans {

    @Bean
    public PriceByPriorityService priceByPriorityService(PriceDao priceDao){
        return new PriceByPriorityService(priceDao);
    }
}
