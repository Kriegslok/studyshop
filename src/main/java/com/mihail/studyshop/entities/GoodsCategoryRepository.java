package com.mihail.studyshop.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GoodsCategoryRepository extends JpaRepository<GoodsCategory, UUID> {
    GoodsCategory getGoodsCategoryByGuid(UUID guid);
    GoodsCategory getParentCategoryByGuid(UUID guid);
    List<GoodsCategory> getChildrenByGuid(UUID guid);
}
