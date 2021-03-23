package com.example.application.backend.domain;

import javax.persistence.*;

@Entity
public class CidadaoMotivo {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cidadao_id")
    private Cidadao cidadao;

    @ManyToOne
    @JoinColumn(name = "motivo_id")
    private Motivo motivo;
}
