package com.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "client")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Integer idClient;

    @Column(name = "f_name_client", nullable = false)
    private String firstName;

    @Column(name = "l_name_client", nullable = false)
    private String lastName;

    @Column(name = "phone_client", nullable = false)
    private Long phone;

    @Column(name = "email_client", nullable = false)
    private String email;

    @Column(name = "localisation_client", nullable = false)
    private String location;
}