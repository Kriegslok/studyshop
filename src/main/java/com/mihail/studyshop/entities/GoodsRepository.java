package com.mihail.studyshop.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GoodsRepository extends JpaRepository<Goods, UUID> {
    Goods getGoodsByGuid(UUID guid);
    List<Goods> getGoodsByName(String name);
    List<Goods> findByName(String name);
    List<Goods> findByGoodsCategory(UUID guid);
    Goods getGoodsByVendorCodeGuid(UUID guid);
}
