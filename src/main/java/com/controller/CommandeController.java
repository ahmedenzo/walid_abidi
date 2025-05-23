package com.controller;


import com.model.Commande;
import com.service.ClientService;
import com.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;
    @Autowired
    private ClientService clientService;

    @GetMapping
    public String listCommandes(Model model) {
        model.addAttribute("commandes", commandeService.getAllCommandes());
        return "commandes/commandes_list";        // <— ici
    }

    @GetMapping("/new")
    public String showCommandeForm(Model model) {
        model.addAttribute("commande", new Commande());
        model.addAttribute("clients", clientService.getAllClients());
        return "commandes/commandes_form";       // <— et ici
    }

    @PostMapping
    public String saveCommande(@ModelAttribute Commande commande) {
        commandeService.saveCommande(commande);
        return "redirect:/commandes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        commandeService.getCommandeById(id)
                .ifPresent(c -> model.addAttribute("commande", c));
        model.addAttribute("clients", clientService.getAllClients());
        return "commandes/commandes_form";       // <— et ici
    }

    @GetMapping("/delete/{id}")
    public String deleteCommande(@PathVariable Integer id) {
        commandeService.deleteCommande(id);
        return "redirect:/commandes";
    }
}
