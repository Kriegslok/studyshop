package com.mihail.studyshop.service;

import com.mihail.studyshop.entities.GoodsCategory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface GoodsCategoryService {

    List<GoodsCategory> getGoodsCategories();

    GoodsCategory getGoodsCategory(UUID guid);

    GoodsCategory getParentCategory(UUID guid);

    List<GoodsCategory> getChildren(UUID guid);

    GoodsCategory addGoodsCategory(GoodsCategory goodsCategory);

    GoodsCategory deleteGoodsCategory(UUID guid);

    GoodsCategory editGoodsCategory(UUID guid, GoodsCategory goodsCategory);

    GoodsCategory addChild(UUID guid, GoodsCategory child);

    GoodsCategory addParent(UUID guid, GoodsCategory parent);

    GoodsCategory removeChild(UUID guid, UUID childGuid);


}
