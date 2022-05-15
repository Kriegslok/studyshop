package com.mihail.studyshop.rest;

import com.mihail.studyshop.entities.Phone;
import com.mihail.studyshop.entities.dto.PhoneDto;
import com.mihail.studyshop.entities.model_mapper.MapperService;
import com.mihail.studyshop.service.ManagerService;
import com.mihail.studyshop.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;


@Controller
@RequestMapping("/")
public class PhoneController {

    private final PhoneService phoneService;
    private final ManagerService managerService;
    private final MapperService mapperService;

    @Autowired
    PhoneController(PhoneService phoneService, ManagerService managerService, MapperService mapperService) {
        this.phoneService = phoneService;
        this.managerService = managerService;
        this.mapperService = mapperService;
    }


    @PostMapping(path = "/addPhone", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    String newPhone(PhoneDto phoneDto) {
        System.out.println(phoneDto);
        managerService.addPhoneToManager(UUID.fromString(phoneDto.getManagerGuid()), mapperService.phoneFromDto(phoneDto));
        return "searchManager";
    }


}
