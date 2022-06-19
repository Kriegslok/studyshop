package com.mihail.studyshop.service;

import com.mihail.studyshop.entities.Goods;
import com.mihail.studyshop.entities.Price;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface GoodsService {

    Goods getGoods(UUID guid);

    Goods addGoods(Goods goods);

    Goods addPriceIfNotExists(UUID guid, Price price);

    List<Goods> getGoodsByCategory(UUID uuid);

    Goods addPrice(UUID guid, Price price);

    Goods getGoodsByVendorCode(UUID guid);
}
