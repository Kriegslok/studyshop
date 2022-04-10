package com.mihail.studyshop.rest;

import com.mihail.studyshop.entities.Manager;
import com.mihail.studyshop.entities.ManagerRepository;
import org.springframework.data.domain.Example;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ManagerController {

    private final ManagerRepository repository;

    ManagerController(ManagerRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/manager")
    Manager newManager(@RequestBody Manager newManager) {
        return repository.save(newManager);
    }

    @GetMapping(value = "/manager{firstName}{lastName}")
    Manager getManager(@RequestParam("firstName")String firstName, @Nullable @RequestParam(name = "lastName") String lastName) {
        System.out.println("getManager " + firstName + " " + lastName);
        List<Manager> managerList = new ArrayList<>();
        if(lastName != null)
        managerList.addAll(repository.findByFirstNameAndLastName(firstName, lastName));
        else managerList.addAll(repository.findByFirstName(firstName));
        if(!managerList.isEmpty()) return managerList.stream().findFirst().get();
        return null;
    }

    @GetMapping(value = "/managers{firstName}{lastName}")
    List<Manager> getManagers (@RequestParam("firstName")String firstName, @Nullable @RequestParam(name = "lastName") String lastName) {
        System.out.println("getManager " + firstName + " " + lastName);
        List<Manager> managerList = new ArrayList<>();
        if(lastName != null)
            managerList.addAll(repository.findByFirstNameAndLastName(firstName, lastName));
        else managerList.addAll(repository.findByFirstName(firstName));
        return managerList;
    }

}
