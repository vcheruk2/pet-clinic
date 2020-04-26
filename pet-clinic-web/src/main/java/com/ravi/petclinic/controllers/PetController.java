package com.ravi.petclinic.controllers;

import com.ravi.petclinic.model.Owner;
import com.ravi.petclinic.model.Pet;
import com.ravi.petclinic.model.PetType;
import com.ravi.petclinic.service.OwnerService;
import com.ravi.petclinic.service.PetService;
import com.ravi.petclinic.service.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
    public String initNewPetForm(Owner owner, Model model){
        Pet pet = new Pet();
        // TODO: Validate to see if the owner is available for a given id
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return PETS_CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/pets/new")
    public String processNewPetForm(Owner owner, @Valid Pet pet, BindingResult result, Model model){
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }

        owner.getPets().add(pet);
        if (result.hasErrors()){
            model.addAttribute("pet", pet);
            return PETS_CREATE_OR_UPDATE_PET_FORM;
        }
        else {
            // TODO: Validate to see if owner is null
            Pet savedPet = petService.save(pet);
            owner.getPets().add(savedPet);
            model.addAttribute(owner);
            return "redirect:/owners/"+owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdatePetForm(@PathVariable Long petId, Model model){
        model.addAttribute("pet", petService.findById(petId));
        return PETS_CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdatePetForm(@Valid Pet pet, BindingResult result, Owner owner, Model model){
        if (result.hasErrors()){
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return PETS_CREATE_OR_UPDATE_PET_FORM;
        }
        else{
            owner.getPets().add(pet);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }
}
