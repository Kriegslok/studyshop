package com.mihail.studyshop.entities.model_mapper;

import com.mihail.studyshop.entities.*;
import com.mihail.studyshop.entities.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface MapperService {

    Manager managerFromDto(ManagerDto managerDto);

    Phone phoneFromDto(PhoneDto phoneDto);

    Vendor vendorFromDto(VendorDto vendorDto);

    VendorCode vendorCodeFromDto(VendorCodeDto vendorCodeDto);

    GoodsCategory goodsCategoryFromDto(GoodsCategoryDto goodsCategoryDto);

    Goods goodsFromDto(GoodsDto goodsDto);

    Price priceFromDto (PriceDto priceDto);
}
