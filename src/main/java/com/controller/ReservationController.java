package com.controller;


import com.model.Client;
import com.model.Mytable;
import com.model.Reservation;
import com.model.ReservationId;
import com.service.ClientService;
import com.service.MytableService;
import com.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private MytableService mytableService;

    @GetMapping
    public String listReservations(Model model) {
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "reservations/reservations_list";   // <— ici
    }

    @GetMapping("/new")
    public String showReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("tables", mytableService.getAllTables());
        return "reservations/reservations_form";   // <— ici
    }

    @PostMapping("/save")

    public String saveReservation(@RequestParam("clientId") Integer clientId,
                                  @RequestParam("tableId") Integer tableId,
                                  @ModelAttribute Reservation reservation) {

        // Utilisation de Optional avec gestion d'erreur
        Client client = clientService.getClientById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + clientId));

        Mytable table = mytableService.getTableById(tableId)
                .orElseThrow(() -> new IllegalArgumentException("Table not found with id: " + tableId));

        // Clé composite
        ReservationId reservationId = new ReservationId();
        reservationId.setIdClient(clientId);
        reservationId.setIdTab(tableId);
        reservation.setId(reservationId);

        // Associer les entités
        reservation.setClient(client);
        reservation.setMytable(table);

        // Enregistrer
        reservationService.saveReservation(reservation);

        return "redirect:/reservations";
    }


    // Dans ReservationController
    @GetMapping("/edit/{idClient}/{idTab}")
    public String showEditForm(@PathVariable Integer idClient,
                               @PathVariable Integer idTab,
                               Model model) {
        ReservationId id = new ReservationId();
        id.setIdClient(idClient);
        id.setIdTab(idTab);
        reservationService.getReservationById(id)
                .ifPresent(r -> model.addAttribute("reservation", r));
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("tables", mytableService.getAllTables());
        return "reservations/reservations_form";
    }

    @GetMapping("/delete/{idClient}/{idTab}")
    public String deleteReservation(@PathVariable Integer idClient,
                                    @PathVariable Integer idTab) {
        ReservationId id = new ReservationId();
        id.setIdClient(idClient);
        id.setIdTab(idTab);
        reservationService.deleteReservation(id);
        return "redirect:/reservations";
    }

}
