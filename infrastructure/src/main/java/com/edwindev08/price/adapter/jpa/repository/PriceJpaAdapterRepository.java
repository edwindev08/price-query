package com.edwindev08.price.adapter.jpa.repository;

import com.edwindev08.price.adapter.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceJpaAdapterRepository extends JpaRepository<PriceEntity, Long> {
    List<PriceEntity> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
            LocalDateTime startDate, LocalDateTime endDate, Long productId, Long brandId
    );
}
