package com.mihail.studyshop.rest;

import com.mihail.studyshop.entities.GoodsCategory;
import com.mihail.studyshop.entities.VendorCode;
import com.mihail.studyshop.entities.dto.VendorCodeDto;
import com.mihail.studyshop.entities.model_mapper.MapperService;
import com.mihail.studyshop.service.GoodsCategoryService;
import com.mihail.studyshop.service.VendorCodeService;
import com.mihail.studyshop.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class VendorCodeController {
    private final VendorCodeService vendorCodeService;
    private  final VendorService vendorService;
    private final MapperService mapperService;
    private final GoodsCategoryService goodsCategoryService;

    @Autowired
    public VendorCodeController(VendorCodeService vendorCodeService, VendorService vendorService, MapperService mapperService, GoodsCategoryService goodsCategoryService) {
        this.vendorCodeService = vendorCodeService;
        this.vendorService = vendorService;
        this.mapperService = mapperService;
        this.goodsCategoryService = goodsCategoryService;
    }

    @PostMapping(path = "/addNewVendorCode", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    String addNewVendorCode(VendorCodeDto vendorCodeDto) {
        vendorCodeService.addVendorCode(mapperService.vendorCodeFromDto(vendorCodeDto));
        return "searchVendor";
    }

    @GetMapping(value = "/getVendorCodes")
    String getVendorCodes(@Nullable @RequestParam("vendorGuid") String vendorGuid,
                          @Nullable @RequestParam(name = "codeGuid") String codeGuid,
                          @Nullable @RequestParam(name = "code") String code,
                          Model model) {
        System.out.println(vendorGuid + " " + codeGuid + " " + code);
        List<VendorCode> vendorCodeList = new ArrayList<>();
        vendorCodeList.addAll(vendorCodeService.getVendorCodeByGuidOrVendorOrCode(vendorGuid, codeGuid, code));
            model.addAttribute("vendorCodeList", vendorCodeList);

            List<GoodsCategory> goodsCategoryList = new ArrayList<>();
            goodsCategoryList.addAll(goodsCategoryService.getGoodsCategories());
            model.addAttribute("goodsCategoryList", goodsCategoryList);
            return "vendorCodeFoundData";
    }

    @GetMapping(value = "/deleteVendorCodes")
    String deleteVendorCodes(@Nullable @RequestParam("vendorGuid") String vendorGuid,
                          @Nullable @RequestParam(name = "codeGuid") String codeGuid,
                          @Nullable @RequestParam(name = "code") String code,
                          Model model) {
        System.out.println("deleteVendorCodes " + vendorGuid + " " + codeGuid + " " + code);
        List<VendorCode> vendorCodeList = new ArrayList<>();
        vendorCodeList.addAll(vendorCodeService.deleteVendorCodeByGuidOrVendorOrCode(vendorGuid, codeGuid, code));
        model.addAttribute("message", "The following vendor codes has been deleted");
        model.addAttribute("vendorCodeList", vendorCodeList);
        return "vendorCodeFoundData";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    String handleCustomException(HttpServletRequest request, IllegalArgumentException ex, Model model) {
        request.setAttribute(ErrorAttributes.ERROR_ATTRIBUTE, ex);
        model.addAttribute("message", ex.getMessage());
        return "errorView";
    }

    @GetMapping("/createTestData")
    public String createTestData(Model model) {
        for (int i = 0; i < 10000; i++) {
            VendorCode vendorCode = new VendorCode(vendorService.getVendorById(UUID.fromString("dd575743-18de-4fb4-aa19-f452b1a490c9")), "Siemens0" + i);
            vendorCodeService.addVendorCode(vendorCode);
        }


        return "errorView";
    }

}
