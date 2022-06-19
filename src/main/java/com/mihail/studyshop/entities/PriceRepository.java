package com.mihail.studyshop.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PriceRepository extends JpaRepository<Price, UUID> {
    List<Price> findByVendorCodeGuid(UUID guid);
    List<Price> findByGoodsGuid(UUID guid);
    Price findByGuid(UUID guid);
    boolean existsByGuid(UUID guid);

}
