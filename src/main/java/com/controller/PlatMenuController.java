package com.controller;


import com.model.Menu;
import com.model.Plat;
import com.model.PlatMenu;
import com.model.PlatMenuId;
import com.service.MenuService;
import com.service.PlatMenuService;
import com.service.PlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/platmenus")
public class PlatMenuController {

    @Autowired
    private PlatMenuService platMenuService;
    @Autowired
    private PlatService platService;
    @Autowired
    private MenuService menuService;

    @GetMapping
    public String listPlatMenus(Model model) {
        model.addAttribute("platMenus", platMenuService.getAllPlatMenus());
        return "platmenus/platmenus_list";       // <— ici
    }

    @GetMapping("/new")
    public String showPlatMenuForm(Model model) {
        model.addAttribute("platMenu", new PlatMenu());
        model.addAttribute("plats", platService.getAllPlats());
        model.addAttribute("menus", menuService.getAllMenus());
        return "platmenus/platmenus_form";       // <— ici
    }

    @PostMapping
    public String savePlatMenu(@RequestParam("platId") Integer platId,
                               @RequestParam("menuId") Integer menuId,
                               @RequestParam("quantity") Integer quantity) {

        Plat plat = platService.getPlatById(platId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid plat ID: " + platId));
        Menu menu = menuService.getMenuById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid menu ID: " + menuId));

        PlatMenuId id = new PlatMenuId();
        id.setIdPlat(platId);
        id.setIdMenu(menuId);

        PlatMenu platMenu = new PlatMenu();
        platMenu.setId(id);
        platMenu.setPlat(plat);
        platMenu.setMenu(menu);
        platMenu.setQuantity(quantity);

        platMenuService.savePlatMenu(platMenu);
        return "redirect:/platmenus";
    }


    // Dans PlatMenuController
    @GetMapping("/edit/{idPlat}/{idMenu}")
    public String showEditForm(@PathVariable Integer idPlat,
                               @PathVariable Integer idMenu,
                               Model model) {
        PlatMenuId id = new PlatMenuId();
        id.setIdPlat(idPlat);
        id.setIdMenu(idMenu);
        platMenuService.getPlatMenuById(id)
                .ifPresent(pm -> model.addAttribute("platMenu", pm));
        model.addAttribute("plats", platService.getAllPlats());
        model.addAttribute("menus", menuService.getAllMenus());
        return "platmenus/platmenus_form";
    }


    @GetMapping("/delete/{idPlat}/{idMenu}")
    public String deletePlatMenu(@PathVariable Integer idPlat,
                                 @PathVariable Integer idMenu) {
        PlatMenuId id = new PlatMenuId();
        id.setIdPlat(idPlat);
        id.setIdMenu(idMenu);
        platMenuService.deletePlatMenu(id);
        return "redirect:/platmenus";
    }

}
