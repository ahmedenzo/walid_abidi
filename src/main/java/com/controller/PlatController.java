package com.controller;


import com.model.Plat;
import com.service.PlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/plats")
public class PlatController {

    @Autowired
    private PlatService platService;

    @GetMapping
    public String listPlats(Model model) {
        model.addAttribute("plats", platService.getAllPlats());
        return "plats/plats_list";               // <— ici
    }

    @GetMapping("/new")
    public String showPlatForm(Model model) {
        model.addAttribute("plat", new Plat());
        return "plats/plats_form";               // <— ici
    }

    @PostMapping
    public String savePlat(@ModelAttribute Plat plat) {
        platService.savePlat(plat);
        return "redirect:/plats";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        platService.getPlatById(id)
                .ifPresent(p -> model.addAttribute("plat", p));
        return "plats/plats_form";               // <— ici
    }

    @GetMapping("/delete/{id}")
    public String deletePlat(@PathVariable Integer id) {
        platService.deletePlat(id);
        return "redirect:/plats";
    }
}
