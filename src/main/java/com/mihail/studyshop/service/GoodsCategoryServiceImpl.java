package com.mihail.studyshop.service;

import com.mihail.studyshop.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService{

    private final GoodsCategoryRepository goodsCategoryRepository;

    @Autowired
    public GoodsCategoryServiceImpl(GoodsCategoryRepository goodsCategoryRepository) {
        this.goodsCategoryRepository = goodsCategoryRepository;
    }

    @Override
    public List<GoodsCategory> getGoodsCategories() {
        List<GoodsCategory> goodsCategoryList = StreamSupport
                .stream(goodsCategoryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return goodsCategoryList;
    }

    @Override
    public GoodsCategory getGoodsCategory(UUID guid) {
        return goodsCategoryRepository.getGoodsCategoryByGuid(guid);
    }

    @Override
    public GoodsCategory getParentCategory(UUID guid) {
        return getGoodsCategory(guid).getParentCategory();
    }

    @Override
    public List<GoodsCategory> getChildren(UUID guid) {
        return getGoodsCategory(guid).getChildrenCategories();
    }

    @Transactional
    @Override
    public GoodsCategory addGoodsCategory(GoodsCategory goodsCategory) {
        if(goodsCategory == null && goodsCategory.getDescription() == null)
            throw new IllegalArgumentException("GoodsCategory or description is null");
        return goodsCategoryRepository.save(goodsCategory);
    }

    @Transactional
    @Override
    public GoodsCategory deleteGoodsCategory(UUID guid) {
        GoodsCategory goodsCategory = getGoodsCategory(guid);
        goodsCategoryRepository.delete(goodsCategory);
        return goodsCategory;
    }

    @Transactional
    @Override
    public GoodsCategory editGoodsCategory(UUID guid, GoodsCategory goodsCategory) {
        GoodsCategory goodsCategoryToEdit = getGoodsCategory(guid);
        goodsCategoryToEdit.setParentCategory(goodsCategory.getParentCategory());
        goodsCategoryToEdit.setChildrenCategories(goodsCategory.getChildrenCategories());
        goodsCategoryToEdit.setDateCreate(goodsCategory.getDateCreate());
        goodsCategoryToEdit.setDescription(goodsCategory.getDescription());
        return goodsCategoryToEdit;
    }

    @Transactional
    @Override
    public GoodsCategory addChild(UUID guid, GoodsCategory child) {
        GoodsCategory goodsCategory = getGoodsCategory(guid);
        goodsCategory.getChildrenCategories().add(child);
        return goodsCategory;
    }

    @Transactional
    @Override
    public GoodsCategory addParent(UUID guid, GoodsCategory parent) {
        GoodsCategory goodsCategory = getGoodsCategory(guid);
        goodsCategory.setParentCategory(parent);
        return goodsCategory;
    }

    @Transactional
    @Override
    public GoodsCategory removeChild(UUID guid, UUID childGuid) {
        GoodsCategory goodsCategory = getGoodsCategory(guid);
        List<GoodsCategory> children = goodsCategory.getChildrenCategories();
        if(children.contains(getGoodsCategory(childGuid))){
            children.remove(getGoodsCategory(childGuid));
            goodsCategory.setChildrenCategories(children);
        } else throw new IllegalArgumentException("Error: " + childGuid + " is not a child of " + guid);
        return goodsCategory;
    }

}
