package com.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "plat_menu")
@Data
public class PlatMenu {
    @EmbeddedId
    private PlatMenuId id;

    @ManyToOne
    @MapsId("idPlat")
    @JoinColumn(name = "id_plat")
    private Plat plat;

    @ManyToOne
    @MapsId("idMenu")
    @JoinColumn(name = "id_menu")
    private Menu menu;

    @Column(name = "Qt_plat", nullable = false)
    private Integer quantity;
}
