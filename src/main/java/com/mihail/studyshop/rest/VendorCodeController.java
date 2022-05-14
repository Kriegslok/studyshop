package com.mihail.studyshop.rest;

import com.mihail.studyshop.entities.VendorCode;
import com.mihail.studyshop.entities.dto.VendorCodeDto;
import com.mihail.studyshop.entities.dto.VendorDto;
import com.mihail.studyshop.entities.model_mapper.MapperService;
import com.mihail.studyshop.service.VendorCodeService;
import com.mihail.studyshop.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class VendorCodeController {
    private final VendorCodeService vendorCodeService;
    private  final VendorService vendorService;
    private final MapperService mapperService;

    @Autowired
    public VendorCodeController(VendorCodeService vendorCodeService, VendorService vendorService, MapperService mapperService) {
        this.vendorCodeService = vendorCodeService;
        this.vendorService = vendorService;
        this.mapperService = mapperService;
    }

    @PostMapping(path = "/addNewVendorCode", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    String addNewVendorCode(VendorCodeDto vendorCodeDto) {
        vendorCodeService.addVendorCode(mapperService.vendorCodeFromDto(vendorCodeDto, vendorService));
        return "searchVendor";
    }

}
