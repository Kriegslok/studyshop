package com.mihail.studyshop.entities.model_mapper;

import com.mihail.studyshop.entities.*;
import com.mihail.studyshop.entities.dto.*;
import com.mihail.studyshop.service.GoodsCategoryService;
import com.mihail.studyshop.service.VendorService;
import com.mihail.studyshop.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class MapperServiceImpl implements MapperService {
    private final VendorService vendorService;
    private final GoodsCategoryService goodsCategoryService;

    @Autowired
    public MapperServiceImpl(VendorService vendorService, GoodsCategoryService goodsCategoryService) {
        this.vendorService = vendorService;
        this.goodsCategoryService = goodsCategoryService;
    }

    @Override
    public Manager managerFromDto(ManagerDto managerDto) {
        Manager manager = new Manager(managerDto.getFirstName(), managerDto.getLastName(), managerDto.getInn());
        manager.setPhones(Arrays.asList(new Phone(managerDto.getPhone(), true)));
        return manager;
    }

    @Override
    public Phone phoneFromDto(PhoneDto phoneDto) {
        Phone phone = new Phone(phoneDto.getPhoneNumber(), Boolean.getBoolean(phoneDto.getIsPrimary()));
        return phone;
    }

    @Override
    public Vendor vendorFromDto(VendorDto vendorDto) {
        Vendor vendor = new Vendor(vendorDto.getName(), vendorDto.getDescription());
        return vendor;
    }

    @Override
    public VendorCode vendorCodeFromDto(VendorCodeDto vendorCodeDto) {
        if (vendorCodeDto.getVendorGuid() != null && UuidUtils.convertableToUuid(vendorCodeDto.getVendorGuid())) {
            VendorCode vendorCode = new VendorCode(vendorService.getVendor(UUID.fromString(vendorCodeDto.getVendorGuid())), vendorCodeDto.getCode().trim());
            return vendorCode;
        } else if (vendorCodeDto.getVendorName() != null) {
            Optional<Vendor> optVendor = vendorService.getByName(vendorCodeDto.getVendorName()).stream().findAny();
            if (optVendor.isPresent()) {
                VendorCode vendorCode = new VendorCode(optVendor.get(), vendorCodeDto.getCode().trim());
                return vendorCode;
            }
        }
        StringBuilder exceptionSb = new StringBuilder();
        exceptionSb.append("Vendor ");
        exceptionSb.append(vendorCodeDto.getVendorGuid());
        exceptionSb.append(" ");
        exceptionSb.append(vendorCodeDto.getVendorName());
        exceptionSb.append("not found");

        throw new IllegalArgumentException(exceptionSb.toString());

    }

    @Override
    public GoodsCategory goodsCategoryFromDto(GoodsCategoryDto goodsCategoryDto) {
        if(goodsCategoryDto == null
                || goodsCategoryDto.getDescription() == null
                || goodsCategoryDto.getParentGuid() == null
                || !UuidUtils.convertableToUuid(goodsCategoryDto.getParentGuid())) throw new IllegalArgumentException("Goods category description or pagent guid not valid");
        GoodsCategory goodsCategory = new GoodsCategory(goodsCategoryService.getGoodsCategory(UUID.fromString(goodsCategoryDto.getParentGuid())),
                goodsCategoryDto.getDescription());
        return goodsCategory;
    }
}
