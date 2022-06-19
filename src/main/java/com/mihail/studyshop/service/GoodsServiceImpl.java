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
    private final PriceService priceService;

    @Autowired
    public GoodsServiceImpl(GoodsRepository goodsRepository, PriceService priceService) {
        this.goodsRepository = goodsRepository;
        this.priceService = priceService;
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
        if(getGoodsByVendorCode(goods.getVendorCode().getGuid()) != null){
            if(!goods.getPriceList().isEmpty()){
                for (Price price : goods.getPriceList()) {
                   addPriceIfNotExists(getGoodsByVendorCode(goods.getVendorCode().getGuid()).getGuid(), price);
                }

            }
            return getGoodsByVendorCode(goods.getVendorCode().getGuid());
        }

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
        priceService.addPrice(price);
        List<Price> prices = goods.getPriceList();
        prices.add(price);
        goods.setPriceList(prices);

        return goods;
    }

    @Override
    @Transactional
    public Goods addPriceIfNotExists(UUID guid,Price price) {
        Goods goods = getGoods(guid);
        price.setVendorCode(goods.getVendorCode());
        price.setGoods(goods);
        System.out.println(price.getVendorCode().getGuid());
        List<Price> priceList = priceService.getPriceByVendorCode(price.getVendorCode().getGuid());
        if(!priceList.contains(price)) {
            priceService.addPrice(price);
            List<Price> prices = goods.getPriceList();
            prices.add(price);
            goods.setPriceList(prices);
        }

        return goods;
    }

    @Override
    public Goods getGoodsByVendorCode(UUID guid) {
        return goodsRepository.getGoodsByVendorCodeGuid(guid);
    }
}
