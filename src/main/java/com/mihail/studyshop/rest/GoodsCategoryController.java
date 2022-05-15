package com.mihail.studyshop.rest;

import com.mihail.studyshop.entities.GoodsCategory;
import com.mihail.studyshop.entities.VendorCode;
import com.mihail.studyshop.entities.dto.GoodsCategoryDto;
import com.mihail.studyshop.entities.model_mapper.MapperService;
import com.mihail.studyshop.service.GoodsCategoryService;
import com.mihail.studyshop.utils.UuidUtils;
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
public class GoodsCategoryController {
    private final GoodsCategoryService goodsCategoryService;
    private final MapperService mapperService;

    @Autowired
    public GoodsCategoryController(GoodsCategoryService goodsCategoryService, MapperService mapperService) {
        this.goodsCategoryService = goodsCategoryService;
        this.mapperService = mapperService;
    }


    @PostMapping(path = "/addNewGoodsCategory", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    String addNewGoodsCategory(GoodsCategoryDto goodsCategoryDto) {
        goodsCategoryService.addGoodsCategory(mapperService.goodsCategoryFromDto(goodsCategoryDto));
        return "searchGoodsCategory";
    }

    @GetMapping(value = "/getGoodsCategory")
    String getGoodsCategory(@Nullable @RequestParam("goodsCategoryGuid") String goodsCategoryGuid, Model model) {
        System.out.println("getGoodsCategory" + " " + goodsCategoryGuid);
        List<GoodsCategory> goodsCategoryList = new ArrayList<>();
        if(goodsCategoryGuid == null) {
            goodsCategoryList.addAll(goodsCategoryService.getGoodsCategories());
            model.addAttribute("goodsCategoryList", goodsCategoryList);
            return "goodsCategoryFoundData";
        }
        if(!UuidUtils.convertableToUuid(goodsCategoryGuid)) throw new IllegalArgumentException("Not valid guid " + goodsCategoryGuid);
        GoodsCategory goodsCategory = goodsCategoryService.getGoodsCategory(UUID.fromString(goodsCategoryGuid));
        goodsCategoryList.add(goodsCategory);
            model.addAttribute("goodsCategoryList", goodsCategoryList);
            return "goodsCategoryFoundData";
    }

    @GetMapping(value = "/getGoodsCategoryChildren")
    String getGoodsCategoryChildren(@Nullable @RequestParam("goodsCategoryGuid") String goodsCategoryGuid, Model model) {
        System.out.println("getGoodsCategoryChildren" + " " + goodsCategoryGuid);
        if(!UuidUtils.convertableToUuid(goodsCategoryGuid)) throw new IllegalArgumentException("Not valid guid " + goodsCategoryGuid);
        List<GoodsCategory> goodsCategoryList = new ArrayList<>();
        goodsCategoryList.addAll(goodsCategoryService.getChildren(UUID.fromString(goodsCategoryGuid)));
        model.addAttribute("goodsCategoryList", goodsCategoryList);
        return "goodsCategoryFoundData";
    }

    @GetMapping(value = "/deleteGoodsCategory")
    String deleteVendorCodes(@RequestParam("goodsCategoryGuid") String goodsCategoryGuid, Model model) {
        System.out.println("deleteGoodsCategory " + goodsCategoryGuid );
        if(!UuidUtils.convertableToUuid(goodsCategoryGuid)) throw new IllegalArgumentException("Not valid guid " + goodsCategoryGuid);
        GoodsCategory goodsCategory = goodsCategoryService.deleteGoodsCategory(UUID.fromString(goodsCategoryGuid));
        model.addAttribute("message", "The following vendor codes has been deleted");
        model.addAttribute("goodsCategory", goodsCategory);
        return "goodsCategoryFoundData";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    String handleCustomException(HttpServletRequest request, IllegalArgumentException ex, Model model) {
        request.setAttribute(ErrorAttributes.ERROR_ATTRIBUTE, ex);
        model.addAttribute("message", ex.getMessage());
        return "errorView";
    }

}
