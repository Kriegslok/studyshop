package com.mihail.studyshop.rest;

import com.mihail.studyshop.entities.Manager;
import com.mihail.studyshop.entities.Vendor;
import com.mihail.studyshop.entities.dto.VendorDto;
import com.mihail.studyshop.entities.model_mapper.MapperService;
import com.mihail.studyshop.service.VendorService;
import com.mihail.studyshop.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @GetMapping(value = "/getVendor{searchString}")
    String getVendor(@RequestParam("searchString") String searchString, Model model) {
        System.out.println("searchVendor " + searchString);
        List<Vendor> vendorList = new ArrayList<>();
        try {
            if(searchString != null && !searchString.isEmpty() && UuidUtils.convertableToUuid(searchString)){
                vendorList.add(vendorService.getVendorById(UUID.fromString(searchString.trim())));
            } else if(searchString != null && !searchString.isEmpty()){
                vendorList.addAll(vendorService.getByName(searchString.trim()));
            } else{
                vendorList.addAll(vendorService.getVendors());
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            model.addAttribute("vendors", vendorList);
            return "vendorFoundData";
        }
    }
}
