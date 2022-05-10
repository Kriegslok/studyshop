package com.mihail.studyshop.service;

import com.mihail.studyshop.entities.Goods;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface GoodsService {

    Goods getGoods(UUID guid);

    Goods addGoods(Goods goods);
}
