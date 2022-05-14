package com.mihail.studyshop.rest;

import com.mihail.studyshop.entities.Vendor;
import com.mihail.studyshop.entities.dto.VendorDto;
import com.mihail.studyshop.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class FirstPageController {
    private final VendorService vendorService;

    @Autowired
    public FirstPageController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model) {
        model.addAttribute("message", "Hello " + name + " " + surname);

        return "hello";
    }

    @GetMapping("/addNewManager")
    public String addNewManagerPage(Model model) {

        return "addNewManager";
    }

    @GetMapping("/addNewVendor")
    public String addNewVendorPage(Model model) {

        return "addNewVendor";
    }


    @GetMapping("/searchManager")
    public String searchManagerPage(Model model) {

        return "searchManager";
    }

    @GetMapping("/addManagersPhone")
    public String addManagersPhonePage(@Nullable @RequestParam(name = "managerGuid") String managerGuid, Model model) {
        model.addAttribute("managerGuid", managerGuid);
        System.out.println("managerGuid " + managerGuid);
        return "addManagersPhone";
    }

    @GetMapping("/addNewVendorCode")
    public String addVendorCodePage(@Nullable @RequestParam(name = "vendorGuid") String vendorGuid, Model model) {
        if (vendorGuid != null && !vendorGuid.isEmpty()) {
            model.addAttribute("vendorGuid", vendorGuid);
            model.addAttribute("vendorName", vendorService.getVendor(UUID.fromString(vendorGuid)).getName());
            System.out.println("managerGuid " + vendorGuid);
            return "addVendorCodeToVendor";
        } else {
            List<Vendor> vendorList = vendorService.getVendors();
            model.addAttribute("vendorList", vendorList);
            return "addVendorCode";
        }
    }

    @GetMapping("/searchVendor")
    public String addNewVendor(Model model) {
        return "searchVendor";
    }

    @GetMapping("/error")
    public String error(Model model) {
        return "error";
    }

    @PostMapping(path = "/error", consumes = {MediaType.ALL_VALUE})
    String error(Map<String, String> map, Model model) {
        model.addAllAttributes(map);
        return "error";
    }
}
