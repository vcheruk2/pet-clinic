package com.ravi.petclinic.controllers;

import com.ravi.petclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        return "index";
    }

    @RequestMapping({"/find"})
    public String notImplemented(){
        return "notImplemented";
    }

    @GetMapping("/{ownerid}")
    public ModelAndView getOwnerDetails(@PathVariable("ownerid") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }
}
