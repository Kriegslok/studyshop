package com.mihail.studyshop.service;

import com.mihail.studyshop.entities.Goods;
import com.mihail.studyshop.entities.GoodsRepository;
import com.mihail.studyshop.entities.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Override
    public List<Goods> getGoodsByCategory(UUID uuid) {
        return goodsRepository.findByGoodsCategory(uuid);
    }

    @Override
    @Transactional
    public Goods addPrice(UUID guid, Price price) {
        Goods goods = getGoods(guid);
        price.setVendorCode(goods.getVendorCode());
        price.setGoods(goods);
        if(goods.getPriceList().add(price))
        return goods;
        throw new IllegalArgumentException("Unable to add price");
    }

    @Override
    public Goods getGoodsByVendorCode(UUID guid) {
        return goodsRepository.getGoodsByVendorCode(guid);
    }
}
