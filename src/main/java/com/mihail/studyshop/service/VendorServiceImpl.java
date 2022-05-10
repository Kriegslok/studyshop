package com.mihail.studyshop.service;

import com.mihail.studyshop.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorCodeService vendorCodeService;
    private final GoodsService goodsService;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository, VendorCodeService vendorCodeService, GoodsService goodsService) {
        this.vendorRepository = vendorRepository;
        this.vendorCodeService = vendorCodeService;
        this.goodsService = goodsService;
    }

    @Transactional
    @Override
    public Vendor addVendor(Vendor vendor) {
        if (vendor.getName() == null)
            throw new IllegalArgumentException("Vendor name is null");
        Vendor vendor1 = new Vendor(vendor.getName(), vendor.getDescription());
        vendor1 = vendorRepository.save(vendor1);

        List<VendorCode> vendorCodes = getVendorCodes(vendor1.getGuid());
        if (vendorCodes.isEmpty() && !vendor.getVendorCodes().isEmpty()) {
            for (VendorCode vendorCode : vendor.getVendorCodes()) {
                vendorCode.setVendor(vendor1);
                vendorCode.setGuid(vendorCodeService.addVendorCode(vendorCode).getGuid());
            }
        }
        vendor1.setVendorCodes(vendorCodes);

        return editVendor(vendor1.getGuid(), vendor1);
    }

    @Override
    public List<Vendor> getVendors() {
        List<Vendor> vendors = StreamSupport
                .stream(vendorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return vendors;
    }

    @Override
    public Vendor getVendorById(UUID guid) {
        return getVendor(guid);
    }

    @Override
    public Vendor getVendor(UUID guid) {
        return vendorRepository.findById(guid).orElseThrow(() ->
                new IllegalArgumentException(
                        "Vendor with id: " + guid + " could not be found"));
    }

    @Override
    public Vendor deleteVendor(UUID guid) {
        Vendor vendor = getVendor(guid);
        vendorRepository.delete(vendor);
        return vendor;
    }

    @Transactional
    @Override
    public Vendor editVendor(UUID guid, Vendor vendor) {
        Vendor vendorToEdit = getVendor(guid);
        vendorToEdit.setVendorCodes(vendor.getVendorCodes());
        vendorToEdit.setDateCreate(vendor.getDateCreate());
        vendorToEdit.setDescription(vendor.getDescription());
        vendorToEdit.setName(vendor.getName());
        return vendorToEdit;
    }

    @Transactional
    @Override
    public Vendor addVendorCodeToVendor(UUID vendorGuid, UUID vendorCodeGuid) {
        Vendor vendor = getVendor(vendorGuid);
        VendorCode vendorCode = vendorCodeService.getVendorCode(vendorCodeGuid);
        vendor.getVendorCodes().add(vendorCode);
        return vendor;
    }

    @Transactional
    @Override
    public Vendor addVendorCodeToVendor(UUID vendorGuid, VendorCode vendorCode) {
        Vendor vendor = getVendor(vendorGuid);
        try {
            vendorCode.setVendor(vendor);
            vendorCodeService.addVendorCode(vendorCode);
            addVendorCodeToVendor(vendorGuid, vendorCode.getGuid());
            return editVendor(vendorGuid, vendor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        vendor.getVendorCodes().add(vendorCode);
        return editVendor(vendorGuid, vendor);
    }

    @Override
    public List<Vendor> getByName(String name) {
        List<Vendor> vendors = new ArrayList<>();
        vendors = vendorRepository.findByName(name);
        if (vendors.isEmpty()) throw new IllegalArgumentException(
                "Vendor with name: " + name + " could not be found");
        return vendors;
    }

    @Override
    public List<VendorCode> getVendorCodes(UUID guid) {
        return getVendor(guid).getVendorCodes();
    }

    @Override
    public List<Goods> getGoods(UUID guid) {
        return getVendor(guid).getGoodsList();
    }

    @Transactional
    @Override
    public Vendor addGoodsToVendor(UUID vendorGuid, UUID goodsGuid) {
        Vendor vendor = getVendor(vendorGuid);
        Goods goods = goodsService.getGoods(goodsGuid);
        vendor.getGoodsList().add(goods);
        return vendor;
    }

    @Transactional
    @Override
    public Vendor addGoodsToVendor(UUID vendorGuid, Goods goods) {
        Vendor vendor = getVendor(vendorGuid);
        try {
            goods.setVendor(vendor);
            goodsService.addGoods(goods);
            addGoodsToVendor(vendorGuid, goods.getGuid());
            return editVendor(vendorGuid, vendor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        vendor.getGoodsList().add(goods);
        return editVendor(vendorGuid, vendor);
    }
}
