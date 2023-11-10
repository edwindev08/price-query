package com.edwindev08.price.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Price {
    private Long id;
    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer applicableRateCode;
    private Long productId;
    private Integer priority;
    private Double finalPrice;
    private String currency;

    public Price() {
        //Default constructor
    }

    public Price(Long id,
                 Long brandId,
                 LocalDateTime startDate,
                 LocalDateTime endDate,
                 Integer applicableRateCode,
                 Long productId,
                 Integer priority,
                 Double finalPrice,
                 String currency) {
        this.id = id;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.applicableRateCode = applicableRateCode;
        this.productId = productId;
        this.priority = priority;
        this.finalPrice = finalPrice;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getApplicableRateCode() {
        return applicableRateCode;
    }

    public void setApplicableRateCode(Integer applicableRateCode) {
        this.applicableRateCode = applicableRateCode;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Objects.equals(id, price.id) && Objects.equals(brandId, price.brandId) && Objects.equals(startDate, price.startDate) && Objects.equals(endDate, price.endDate) && Objects.equals(applicableRateCode, price.applicableRateCode) && Objects.equals(productId, price.productId) && Objects.equals(priority, price.priority) && Objects.equals(finalPrice, price.finalPrice) && Objects.equals(currency, price.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandId, startDate, endDate, applicableRateCode, productId, priority, finalPrice, currency);
    }
}
