package com.ravi.petclinic.controllers;

import com.ravi.petclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"/vet","/vets","/vets/index","/vets/index.html"})
    public String listVets(Model model){
        model.addAttribute("vets", this.vetService.findAll());
        return "vets/vets";
    }
}
