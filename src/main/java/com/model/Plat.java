package com.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "plat")
@Data
public class Plat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plat")
    private Integer idPlat;

    @Column(name = "prix_plat", nullable = false)
    private Double prixPlat;

    @Column(name = "disc_plat", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "name_plat", nullable = false)
    private String name;

    @Column(name = "avis_plat", columnDefinition = "TEXT")
    private String avis;
}