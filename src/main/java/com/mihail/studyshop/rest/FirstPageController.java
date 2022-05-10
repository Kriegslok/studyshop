package com.mihail.studyshop.rest;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class FirstPageController {
    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model){
        model.addAttribute("message", "Hello " + name + " " + surname);

        //System.out.println("Hello, " + name + " " + surname);
        return "hello";
    }

    @GetMapping("/addNewManager")
    public String addNewManagerPage(Model model){

        return "addNewManager";
    }

    @GetMapping("/searchManager")
    public String searchManagerPage(Model model){

        return "searchManager";
    }

    @GetMapping("/addManagersPhone")
    public String addManagersPhonePage(@Nullable @RequestParam(name = "managerGuid") String managerGuid, Model model){
        model.addAttribute("managerGuid", managerGuid);
        System.out.println("managerGuid " + managerGuid);
        return "addManagersPhone";
    }
}
