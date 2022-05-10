package com.mihail.studyshop.service;

import com.mihail.studyshop.entities.Goods;
import com.mihail.studyshop.entities.Manager;
import com.mihail.studyshop.entities.Vendor;
import com.mihail.studyshop.entities.VendorCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface VendorService {

    Vendor addVendor(Vendor vendor);

    List<Vendor> getVendors();

    Vendor getVendorById(UUID guid);

    Vendor getVendor(UUID guid);

    Vendor deleteVendor(UUID guid);

    Vendor editVendor(UUID guid, Vendor vendor);

    Vendor addVendorCodeToVendor(UUID vendorGuid, UUID vendorCodeGuid);

    Vendor addVendorCodeToVendor(UUID vendorGuid, VendorCode vendorCode);

    List<Vendor> getByName(String name);

    List<VendorCode> getVendorCodes(UUID guid);

    List<Goods> getGoods(UUID guid);

    Vendor addGoodsToVendor(UUID vendorGuid, UUID goodsGuid);

    Vendor addGoodsToVendor(UUID vendorGuid, Goods goods);

}
