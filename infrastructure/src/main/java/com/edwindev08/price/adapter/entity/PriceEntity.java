package com.edwindev08.price.adapter.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "PRICES")
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "BRAND_ID")
    private Long brandId;
    @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    @Column(name = "START_DATE")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    @Column(name = "END_DATE")
    private LocalDateTime endDate;
    @Column(name = "PRICE_LIST")
    private Integer priceList;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "CURR")
    private String currency;

    public PriceEntity() {
        //No args constructor
    }

    @JsonCreator
    public PriceEntity(Long brandId,
                       LocalDateTime startDate,
                       LocalDateTime endDate,
                       Integer priceList,
                       Long productId,
                       Integer priority,
                       Double price,
                       String currency
    ) {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getPriority() {
        return priority;
    }

    public Double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }
}
