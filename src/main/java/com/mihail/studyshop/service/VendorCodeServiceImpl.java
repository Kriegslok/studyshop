package com.mihail.studyshop.service;

import com.mihail.studyshop.entities.*;
import com.mihail.studyshop.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VendorCodeServiceImpl implements VendorCodeService{

    private final VendorCodeRepository vendorCodeRepository;
    private VendorService vendorService;


    public void setVendorService(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @Autowired
    public VendorCodeServiceImpl(VendorCodeRepository vendorCodeRepository) {
        this.vendorCodeRepository = vendorCodeRepository;
    }

    @Transactional
    @Override
    public VendorCode addVendorCode(VendorCode vendorCode) throws IllegalArgumentException {
        if (vendorCode.getCode() == null || vendorCode.getVendor() == null)
            throw new IllegalArgumentException("Vendor or vendor code is null");
        if(exists(vendorCode)) throw new IllegalArgumentException("Vendor code already exists");
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
    public VendorCode getVendorCode(UUID guid) throws IllegalArgumentException{
        return vendorCodeRepository.findById(guid).orElseThrow(() ->
                new IllegalArgumentException(
                        "Vendor code with id: " + guid + " could not be found"));
    }

    @Override
    public List<VendorCode> getVendorCodeByCode(String code) {
        List<VendorCode> vendorCodeList = new ArrayList<>();
        vendorCodeList.addAll(vendorCodeRepository.findByCode(code));
        return vendorCodeList;
    }

    @Override
    public List<VendorCode> getVendorCodeByGuidOrVendorOrCode(String vendorGuid, String guid, String code){
        List<VendorCode> vendorCodeList = new ArrayList<>();
        if(UuidUtils.convertableToUuid(guid)) vendorCodeList.add(getVendorCode(UUID.fromString(guid.trim())));
        else if(UuidUtils.convertableToUuid(vendorGuid)){
            vendorCodeList.addAll(vendorService.getVendorCodes(UUID.fromString(vendorGuid.trim())).stream()
                    .filter(item->(code != null && !code.isEmpty())? item.getCode().equals(code) : true)
                    .collect(Collectors.toList()));
        }
        return vendorCodeList;
    }

    @Override
    public List<VendorCode> deleteVendorCodeByGuidOrVendorOrCode(String vendorGuid, String guid, String code){
        List<VendorCode> vendorCodeList = new ArrayList<>();
        if(UuidUtils.convertableToUuid(guid)) vendorCodeList.add(getVendorCode(UUID.fromString(guid.trim())));
        else if(UuidUtils.convertableToUuid(vendorGuid)){
            vendorCodeList.addAll(vendorService.getVendorCodes(UUID.fromString(vendorGuid.trim())).stream()
                    .filter(item->(code != null && !code.isEmpty())? item.getCode().equals(code) : true)
                    .collect(Collectors.toList()));
        }
        for(VendorCode vendorCode: vendorCodeList){
            deleteVendorCode(vendorCode.getGuid());
        }
        return vendorCodeList;
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

    @Override
    public boolean exists(VendorCode vendorCode) {
        return (getVendorCodeByCode(vendorCode.getCode()).contains(vendorCode));
    }


}
