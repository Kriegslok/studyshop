package com.mihail.studyshop.service;


import com.mihail.studyshop.entities.Phone;
import com.mihail.studyshop.entities.PhoneRepository;
import com.mihail.studyshop.entities.Price;
import com.mihail.studyshop.entities.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PriceServiceImpl implements PriceService {


    private final PriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository) {

        this.priceRepository = priceRepository;
    }

    @Transactional
    @Override
    public Price addPrice(Price price) {
        if (price.getPrice() == null || price.getGoods() == null)
            throw new IllegalArgumentException("Price is null or Goods is null");

        return priceRepository.save(price);
    }




    @Override
    public List<Price> getPrice() {
        List<Price> priceList = StreamSupport
                .stream(priceRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return priceList;
    }

    @Override
    public List<Price> getPriceByVendorCode(UUID guid) {
        List<Price> priceList = StreamSupport
                .stream(priceRepository.findByVendorCodeGuid(guid).spliterator(), false)
                .collect(Collectors.toList());
        if(priceList.isEmpty())  throw new IllegalArgumentException("No price found on GUID " + guid);
        else for (Price price : priceList){
            System.out.println(price.getPrice());
        }
        return priceList;
    }

    @Override
    public List<Price> getPriceByGoods(UUID guid) {
        List<Price> priceList = StreamSupport
                .stream(priceRepository.findByGoodsGuid(guid).spliterator(), false)
                .collect(Collectors.toList());
        return priceList;
    }

    @Override
    public Price getGetById(UUID guid) {
        return priceRepository.findById(guid).orElseThrow(() ->
                new IllegalArgumentException(
                        "Price with id: " + guid + " could not be found"));
    }


    @Transactional
    @Override
    public Price deletePrice(UUID guid) {
        Price price = getGetById(guid);
        priceRepository.delete(price);
        return price;
    }

    @Transactional
    @Override
    public Price editPrice(UUID priceGuid, Price price) {
        Price priceToEdit = getGetById(priceGuid);
        priceToEdit.setPrice(price.getPrice());
        priceToEdit.setComment(price.getComment());
        priceToEdit.setGoods(price.getGoods());
        priceToEdit.setVendorCode(price.getVendorCode());
        priceToEdit.setDateCreate(price.getDateCreate());
        return priceToEdit;
    }
}
