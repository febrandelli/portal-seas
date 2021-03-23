package com.example.application.backend.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
public class Cidadao {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @ManyToOne
    @JoinColumn(name = "ID_SEXO")
    private Sexo sexo;

    @ManyToOne
    @JoinColumn(name = "ID_COR")
    private Cor cor;

    @ManyToOne
    @JoinColumn(name = "ID_CIDADE")
    private Cidade cidadeNascimento;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cidadao", cascade = CascadeType.ALL)
    private Set<CidadaoMotivo> motivos;

}
