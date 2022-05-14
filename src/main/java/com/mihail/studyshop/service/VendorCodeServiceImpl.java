package com.mihail.studyshop.service;

import com.mihail.studyshop.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VendorCodeServiceImpl implements VendorCodeService{

    private final VendorCodeRepository vendorCodeRepository;


    @Autowired
    public VendorCodeServiceImpl(VendorCodeRepository vendorCodeRepository) {
        this.vendorCodeRepository = vendorCodeRepository;
    }

    @Transactional
    @Override
    public VendorCode addVendorCode(VendorCode vendorCode) {
        if (vendorCode.getCode() == null || vendorCode.getVendor() == null)
            throw new IllegalArgumentException("Vendor or vendor code is null");

        return vendorCodeRepository.save(vendorCode);
    }

    @Override
    public List<VendorCode> getVendorCodes() {
        List<VendorCode> vendorCodes = StreamSupport
                .stream(vendorCodeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return vendorCodes;
    }

    @Override
    public VendorCode getVendorCodeById(UUID guid) {
        return getVendorCode(guid);
    }

    @Override
    public VendorCode getVendorCode(UUID guid) {
        return vendorCodeRepository.findById(guid).orElseThrow(() ->
                new IllegalArgumentException(
                        "Vendor code with id: " + guid + " could not be found"));
    }

    @Override
    public VendorCode getVendorCodeByCode(String code) {
        VendorCode vendorCode = vendorCodeRepository.findByCode(code);
        if(vendorCode == null) throw  new IllegalArgumentException(
                "Vendor code with code: " + code + " could not be found");
        return vendorCode;

    }

    @Transactional
    @Override
    public VendorCode deleteVendorCode(UUID guid) {
        VendorCode vendorCode = getVendorCode(guid);
        vendorCodeRepository.delete(vendorCode);
        return vendorCode;
    }

    @Transactional
    @Override
    public VendorCode editVendorCode(UUID guid, VendorCode vendorCode) {
        VendorCode vendorCodeToEdit = getVendorCode(guid);
        vendorCodeToEdit.setVendor(vendorCode.getVendor());
        vendorCodeToEdit.setCode(vendorCode.getCode());
        vendorCodeToEdit.setDateCreate(vendorCode.getDateCreate());
        return vendorCodeToEdit;
    }
}
