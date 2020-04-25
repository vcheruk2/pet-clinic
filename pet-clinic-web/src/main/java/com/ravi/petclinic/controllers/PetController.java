package com.ravi.petclinic.controllers;

import com.ravi.petclinic.model.Owner;
import com.ravi.petclinic.model.Pet;
import com.ravi.petclinic.model.PetType;
import com.ravi.petclinic.service.OwnerService;
import com.ravi.petclinic.service.PetService;
import com.ravi.petclinic.service.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 4/25/2020 */

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private static final String PETS_CREATE_OR_UPDATE_PET_FORM = "/pets/createOrUpdatePetForm";

    private final OwnerService ownerService;
    private final PetService petService;
    private final PetTypeService petTypeService;

    public PetController(OwnerService ownerService, PetService petService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId){
        return ownerService.findById(ownerId);
    }

    @ModelAttribute("types")
    public Collection<PetType> petTypes(){
        return petTypeService.findAll();
    }

    @GetMapping("/pets/new")
    public String initNewPetForm(@PathVariable Long ownerId, Model model){
        Pet pet = new Pet();
        // TODO: Validate to see if the owner is available for a given id
        pet.setOwner(ownerService.findById(ownerId));
        model.addAttribute("pet", pet);
        return PETS_CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/pets/new")
    public String processNewPetForm(@Valid Pet pet, BindingResult result, @PathVariable Long ownerId, Model model){
        if (result.hasErrors())
            return PETS_CREATE_OR_UPDATE_PET_FORM;
        else {
            // TODO: Validate to see if owner is null
            Owner owner = ownerService.findById(ownerId);
            owner.getPets().add(pet);
            return "/owners/" + owner.getId();
        }
    }
}
