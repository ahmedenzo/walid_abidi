package com.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Embeddable
@Data
public class PlatMenuId implements Serializable {
    @Column(name = "id_plat")
    private Integer idPlat;

    @Column(name = "id_menu")
    private Integer idMenu;
}
