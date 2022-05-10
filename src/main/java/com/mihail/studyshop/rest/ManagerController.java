package com.mihail.studyshop.rest;

import com.mihail.studyshop.entities.Manager;
import com.mihail.studyshop.entities.ManagerRepository;
import com.mihail.studyshop.entities.Phone;
import com.mihail.studyshop.entities.synthetic.SyntheticManager;
import com.mihail.studyshop.service.ManagerService;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

//@RestController
@Controller
@RequestMapping("/")
public class ManagerController {

    private final ManagerService managerService;

    ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

//    @PostMapping("/manager")
//    Manager newManager(@RequestBody Manager newManager) {
//        return managerService.addManger(newManager);
//    }

    @PostMapping(path = "/manager", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    Manager newManager(SyntheticManager newManager) {
        Manager manager = new Manager(newManager.getFirstName(), newManager.getLastName(), newManager.getInn());
        manager.setPhones(Arrays.asList(new Phone(newManager.getPhone(), true)));
        return managerService.addManger(manager);
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

    @GetMapping(value = "/managers")
    List<Manager> getManagers(@RequestParam("firstName") String firstName, @Nullable @RequestParam(name = "lastName") String lastName) {
        System.out.println("getManager " + firstName + " " + lastName);
        List<Manager> managerList = new ArrayList<>();

        try {
            if (lastName != null)
                managerList.addAll(managerService.getByFirstAndLastName(firstName, lastName));
            else managerList.addAll(managerService.findByFirstName(firstName));
        } catch (IllegalArgumentException e) {
            return managerList;
        }

        return managerList;
    }

}
