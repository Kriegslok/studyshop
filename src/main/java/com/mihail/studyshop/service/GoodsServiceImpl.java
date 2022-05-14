package com.mihail.studyshop.service;

import com.mihail.studyshop.entities.Goods;
import com.mihail.studyshop.entities.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GoodsServiceImpl implements GoodsService{

    private final GoodsRepository goodsRepository;

    @Autowired
    public GoodsServiceImpl(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @Override
    public Goods getGoods(UUID guid) {
        return goodsRepository.findById(guid).orElseThrow(() ->
                new IllegalArgumentException(
                        "Goods with id: " + guid + " could not be found"));
    }

    @Override
    public Goods addGoods(Goods goods) {
        if(goods.getName() == null || goods.getVendor() == null || goods.getVendorCode() == null)
            throw new IllegalArgumentException("Goods name, price, vendor or vendor code is null");

        return goodsRepository.save(goods);
    }
}
