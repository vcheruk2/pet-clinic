package com.ravi.petclinic.controllers;

import com.ravi.petclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({"/owners","/owner"})
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"","/", "/index.html", "/index"})
    public String listOwners(Model model){
        model.addAttribute("owners", this.ownerService.findAll());
        return "owners/owners";
    }

    @RequestMapping({"/find"})
    public String notImplemented(){
        return "notImplemented";
    }
}
