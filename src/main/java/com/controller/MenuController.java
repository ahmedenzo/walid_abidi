package com.controller;


import com.model.Menu;
import com.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public String listMenus(Model model) {
        model.addAttribute("menus", menuService.getAllMenus());
        return "menus/menus_list";               // <— ici
    }

    @GetMapping("/new")
    public String showMenuForm(Model model) {
        model.addAttribute("menu", new Menu());
        return "menus/menus_form";               // <— ici
    }

    @PostMapping
    public String saveMenu(@ModelAttribute Menu menu) {
        menuService.saveMenu(menu);
        return "redirect:/menus";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        menuService.getMenuById(id)
                .ifPresent(m -> model.addAttribute("menu", m));
        return "menus/menus_form";               // <— ici
    }

    @GetMapping("/delete/{id}")
    public String deleteMenu(@PathVariable Integer id) {
        menuService.deleteMenu(id);
        return "redirect:/menus";
    }
}
