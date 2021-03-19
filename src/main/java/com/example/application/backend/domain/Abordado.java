package com.example.application.backend.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Abordado {

    @Id
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

}
