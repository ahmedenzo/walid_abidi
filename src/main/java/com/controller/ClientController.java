package com.controller;

import com.model.Client;
import com.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String listClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients/clients_list";
    }

    @GetMapping("/new")
    public String showClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "clients/clients_form"; // corrigé
    }

    @PostMapping
    public String saveClient(@ModelAttribute Client client) {
        clientService.saveClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        clientService.getClientById(id).ifPresent(client -> model.addAttribute("client", client));
        return "clients/clients_form"; // corrigé
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
        return "redirect:/clients";
    }
}
