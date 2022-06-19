package com.mihail.studyshop.service;

import com.mihail.studyshop.entities.Price;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface PriceService {


    public Price addPrice(Price price);

    public List<Price> getPrice();

    public List<Price> getPriceByVendorCode(UUID guid);

    public List<Price> getPriceByGoods(UUID guid);

    public Price getGetById(UUID guid);

    public Price deletePrice(UUID guid);

    public Price editPrice(UUID priceGuid, Price price);


}
