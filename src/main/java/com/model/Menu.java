package com.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "menu")
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu")
    private Integer idMenu;

    @Column(name = "name_menu")
    private String name;
}