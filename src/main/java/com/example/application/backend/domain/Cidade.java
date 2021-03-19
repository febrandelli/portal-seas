package com.example.application.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CIDADES")
public class Cidade {

    @Id
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO")
    private Estado idEstado;
}
