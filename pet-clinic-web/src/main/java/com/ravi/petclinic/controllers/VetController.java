package com.ravi.petclinic.controllers;

import com.ravi.petclinic.model.Vet;
import com.ravi.petclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping({"/vet","/vets","/vets/index","/vets/index.html", "/vets.html"})
    public String listVets(Model model){
        model.addAttribute("vets", this.vetService.findAll());
        return "vets/vets";
    }

    @GetMapping("/api/vets")
    public @ResponseBody Set<Vet> getVetsJson(){

        return vetService.findAll();
    }
}
