package com.ravi.petclinic.controllers;

import com.ravi.petclinic.model.Owner;
import com.ravi.petclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping({"/owners"})
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /*@GetMapping({"","/", "/index.html", "/index"})
    public String listOwners(Model model){
        model.addAttribute("owners", this.ownerService.findAll());
        return "index";
    }*/

    @GetMapping({"/find"})
    public String notImplemented(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping({""})
    public String processFindForm(Owner owner, BindingResult result, Model model){
        if (owner.getLastName() == null)
            owner.setLastName("");

        //List<Owner> results = ownerService.findAllByLastNameLike("%"+owner.getLastName()+"%");
        List<Owner> results = ownerService.findAllByLastNameLike(owner.getLastName());

        if (results.isEmpty()){ // No user
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }
        else if(results.size() == 1){ // Single User
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        }
        else{ // Multiple users
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/{ownerid}")
    public ModelAndView getOwnerDetails(@PathVariable("ownerid") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }
}
