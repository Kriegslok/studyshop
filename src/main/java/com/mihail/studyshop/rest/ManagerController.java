package com.mihail.studyshop.rest;

import com.mihail.studyshop.entities.Manager;
import com.mihail.studyshop.entities.Phone;
import com.mihail.studyshop.entities.dto.ManagerDto;
import com.mihail.studyshop.entities.model_mapper.MapperService;
import com.mihail.studyshop.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

//@RestController
@Controller
@RequestMapping("/")
public class ManagerController {

    private final ManagerService managerService;
    private final MapperService mapperService;

    @Autowired
    ManagerController(ManagerService managerService, MapperService mapperService) {
        this.managerService = managerService;
        this.mapperService = mapperService;
    }

//    @PostMapping("/manager")
//    Manager newManager(@RequestBody Manager newManager) {
//        return managerService.addManger(newManager);
//    }

    @PostMapping(path = "/manager", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    Manager newManager(ManagerDto newManager) {
        return managerService.addManger(mapperService.managerFromDto(newManager));
    }

    @GetMapping(value = "/manager{firstName}{lastName}")
    String getManager(@RequestParam("firstName") String firstName, @Nullable @RequestParam(name = "lastName") String lastName, Model model) {
        System.out.println("getManager " + firstName + " " + lastName);
        List<Manager> managerList = new ArrayList<>();
        try {
            if(firstName != null && lastName != null && !firstName.isEmpty() && !lastName.isEmpty()){
                managerList.addAll(managerService.getByFirstAndLastName(firstName, lastName));
            } else if(firstName != null && !firstName.isEmpty()){
                managerList.addAll(managerService.findByFirstName(firstName));
            } else if(lastName != null && !lastName.isEmpty()){
                managerList.addAll(managerService.findByLastName(lastName));
            }else{
                managerList.addAll(managerService.getManagers());
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            model.addAttribute("managers", managerList);
            return "managerFoundData";
        }
    }

    @RequestMapping(value = "/managers", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = MediaType.ALL_VALUE )
    @ResponseBody
    List<Manager> getManagers(@Nullable @RequestParam("firstName") String firstName, @Nullable @RequestParam(name = "lastName") String lastName) {
        System.out.println("getManager " + firstName + " " + lastName);
        List<Manager> managerList = new ArrayList<>();

        try {
            if(firstName == null && lastName == null){
                managerList.addAll(managerService.getManagers());
            }else if (lastName != null)
                managerList.addAll(managerService.getByFirstAndLastName(firstName, lastName));
            else managerList.addAll(managerService.findByFirstName(firstName));
        } catch (IllegalArgumentException e) {
            return managerList;
        }

        return managerList;
    }

}
