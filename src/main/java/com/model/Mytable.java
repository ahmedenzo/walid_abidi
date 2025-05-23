package com.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mytable")
@Data
public class Mytable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tab")
    private Integer idTab;

    @Column(name = "nbr_place", nullable = false)
    private Integer numberOfSeats;

    @Column(name = "localisation_table", nullable = false)
    private String location;
}