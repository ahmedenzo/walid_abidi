package com.controller;


import com.model.Mytable;
import com.service.MytableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tables")
public class MytableController {

    @Autowired
    private MytableService mytableService;

    @GetMapping
    public String listTables(Model model) {
        model.addAttribute("tables", mytableService.getAllTables());
        return "tables/tables_list";             // <— ici
    }

    @GetMapping("/new")
    public String showTableForm(Model model) {
        model.addAttribute("mytable", new Mytable());
        return "tables/tables_form";             // <— ici
    }

    @PostMapping
    public String saveTable(@ModelAttribute Mytable mytable) {
        mytableService.saveTable(mytable);
        return "redirect:/tables";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        mytableService.getTableById(id)
                .ifPresent(t -> model.addAttribute("mytable", t));
        return "tables/tables_form";             // <— ici
    }

    @GetMapping("/delete/{id}")
    public String deleteTable(@PathVariable Integer id) {
        mytableService.deleteTable(id);
        return "redirect:/tables";
    }
}
