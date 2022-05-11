package com.mihail.studyshop.rest;

import com.mihail.studyshop.entities.dto.VendorDto;
import com.mihail.studyshop.entities.model_mapper.MapperService;
import com.mihail.studyshop.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class VendorController {

    private final VendorService vendorService;
    private final MapperService mapperService;

    @Autowired
    public VendorController(VendorService vendorService, MapperService mapperService) {
        this.vendorService = vendorService;
        this.mapperService = mapperService;
    }

    @PostMapping(path = "/addNewVendor", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    String addNewVendor(VendorDto vendorDto) {
        vendorService.addVendor(mapperService.vendorFromDto(vendorDto));
        return "/addNewVendor";
    }
}
