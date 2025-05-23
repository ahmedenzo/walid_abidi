package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class ReservationId implements Serializable {
    @Column(name = "id_client")
    private Integer idClient;

    @Column(name = "id_tab")
    private Integer idTab;
}
