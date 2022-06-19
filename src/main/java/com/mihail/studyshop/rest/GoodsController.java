package com.mihail.studyshop.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mihail.studyshop.entities.Goods;
import com.mihail.studyshop.entities.GoodsCategory;
import com.mihail.studyshop.entities.dto.GoodsCategoryDto;
import com.mihail.studyshop.entities.dto.GoodsDto;
import com.mihail.studyshop.entities.dto.PriceDto;
import com.mihail.studyshop.entities.model_mapper.MapperService;
import com.mihail.studyshop.service.GoodsCategoryService;
import com.mihail.studyshop.service.GoodsService;
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
public class GoodsController {
    private final GoodsService goodsService;
    private final MapperService mapperService;

    @Autowired
    public GoodsController(GoodsService goodsService, MapperService mapperService) {
        this.goodsService = goodsService;
        this.mapperService = mapperService;
    }

    @RequestMapping(value = "/addNewGoods", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    Goods addNewGoods(@RequestBody GoodsDto goodsDto){
        System.out.println(goodsDto.toString());

        return goodsService.addGoods(mapperService.goodsFromDto(goodsDto));
        //goodsService.addGoods(mapperService.goodsFromDto(goodsDto));
        //TODO сделать маппинг из ДТО чтобы создавались объект "товар" со вложенным объектом "цена". Если товар уже есть, цена должна к нему добавляться.
        //return "searchVendor";
    }


    @RequestMapping(value = "/getGoods", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    List<Goods> getGoods(@RequestBody String json) throws JsonProcessingException {
        System.out.println(json);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        String goodsCategoryGuid = jsonNode.get("goodsGuid").asText();
        String vendorCodeGuid = jsonNode.get("vendorCodeGuid").asText();
        System.out.println("getGoods by category" + " " + goodsCategoryGuid);
        System.out.println("getGoods by vendorCode" + " " + vendorCodeGuid);
        List<Goods> goodsList = new ArrayList<>();

        if(!UuidUtils.convertableToUuid(goodsCategoryGuid) && !UuidUtils.convertableToUuid(vendorCodeGuid))
            throw new IllegalArgumentException("Not valid guid " + goodsCategoryGuid + " " + vendorCodeGuid);
        if(UuidUtils.convertableToUuid(goodsCategoryGuid))
        goodsList.addAll(goodsService.getGoodsByCategory(UUID.fromString(goodsCategoryGuid)));
        else if (UuidUtils.convertableToUuid(vendorCodeGuid))
            goodsList.add(goodsService.getGoodsByVendorCode(UUID.fromString(vendorCodeGuid)));
        return goodsList;
    }

    @PostMapping(path = "/addPrice", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    String addPrice(@Nullable @RequestParam("goodsGuid") String goodsGuid, PriceDto priceDto, Model model) {
        System.out.println("addPrice" + " " + goodsGuid);
        if(!UuidUtils.convertableToUuid(goodsGuid)) throw new IllegalArgumentException("Not valid guid " + goodsGuid);
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(goodsService.addPrice(UUID.fromString(goodsGuid), mapperService.priceFromDto(priceDto)));
        model.addAttribute("goodsList", goodsList);
        return "goodsFoundData";
    }
/*
    @GetMapping(value = "/deleteGoodsCategory")
    String deleteVendorCodes(@RequestParam("goodsCategoryGuid") String goodsCategoryGuid, Model model) {
        System.out.println("deleteGoodsCategory " + goodsCategoryGuid );
        if(!UuidUtils.convertableToUuid(goodsCategoryGuid)) throw new IllegalArgumentException("Not valid guid " + goodsCategoryGuid);
        GoodsCategory goodsCategory = goodsCategoryService.deleteGoodsCategory(UUID.fromString(goodsCategoryGuid));
        model.addAttribute("message", "The following vendor codes has been deleted");
        model.addAttribute("goodsCategory", goodsCategory);
        return "goodsCategoryFoundData";
    }
    */


    @ExceptionHandler(IllegalArgumentException.class)
    String handleCustomException(HttpServletRequest request, IllegalArgumentException ex, Model model) {
        request.setAttribute(ErrorAttributes.ERROR_ATTRIBUTE, ex);
        model.addAttribute("message", ex.getMessage());
        return "errorView";
    }

}
