package com.edwindev08.price.rest.controller;

import com.edwindev08.price.model.dto.PriceDto;
import com.edwindev08.price.model.exception.PriceNotFoundException;
import com.edwindev08.price.query.PriceByIdsAndDateHandler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/price")
public class PriceQueryController {

    private final PriceByIdsAndDateHandler priceByIdsAndDateHandler;

    public PriceQueryController(PriceByIdsAndDateHandler priceByIdsAndDateHandler) {
        this.priceByIdsAndDateHandler = priceByIdsAndDateHandler;
    }

    @GetMapping
    public ResponseEntity<PriceDto> getPriceByIdsAndDate(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") LocalDateTime startDate,
            @RequestParam Long productId,
            @RequestParam Long brandId) {

        var resultPrice = priceByIdsAndDateHandler.execute(startDate, productId, brandId)
                .orElseThrow(PriceNotFoundException::new);

        return ResponseEntity.ok(resultPrice);

    }

}
