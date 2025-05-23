package com.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservation")
@Data
public class Reservation {
    @EmbeddedId
    private ReservationId id;

    @ManyToOne
    @MapsId("idClient")
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @MapsId("idTab")
    @JoinColumn(name = "id_tab")
    private Mytable mytable;

    @Column(name = "date_reservation")
    private LocalDate dateReservation;

    @Column(name = "heure_debut_reservation")
    private LocalTime heureDebut;

    @Column(name = "heure_fin_reservation")
    private LocalTime heureFin;
}
