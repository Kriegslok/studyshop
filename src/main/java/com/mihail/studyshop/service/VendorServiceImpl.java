package com.mihail.studyshop.service;

import com.mihail.studyshop.entities.Vendor;
import com.mihail.studyshop.entities.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VendorServiceImpl implements VendorService{

    private final VendorRepository vendorRepository;
    private final VendorCodeService vendorCodeService;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository, VendorCodeService vendorCodeService) {
        this.vendorRepository = vendorRepository;
        this.vendorCodeService = vendorCodeService;
    }

    @Override
    public Vendor addVendor(Vendor vendor) {
        return null;
    }

    @Override
    public List<Vendor> getVendors() {
        return null;
    }

    @Override
    public Vendor getVendorById(UUID guid) {
        return null;
    }

    @Override
    public Vendor getVendor(UUID guid) {
        return null;
    }

    @Override
    public Vendor deleteVendor(UUID guid) {
        return null;
    }

    @Override
    public Vendor editVendor(UUID guid, Vendor vendor) {
        return null;
    }

    @Override
    public Vendor addVendorCodeToVendor(UUID vendorGuid, UUID vendorCodeGuid) {
        return null;
    }

    @Override
    public List<Vendor> getByName(String name) {
        return null;
    }
}
