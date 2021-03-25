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

    @ManyToOne
    @JoinColumn(name = "ID_PRINCIPAL_RENDA")
    private FonteDeRenda fonteDeRenda;

    @Column(name = "DESEJA_SAIR_RUA")
    private Boolean querSairDasRuas;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cidadao_motivo", joinColumns = {
            @JoinColumn(name = "cidadao_id")}, inverseJoinColumns = {
            @JoinColumn(name = "motivo_id")
    })
    private Set<Motivo> motivos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cidadao_caso_especial", joinColumns = {
            @JoinColumn(name = "cidadao_id")}, inverseJoinColumns = {
            @JoinColumn(name = "caso_especial_id")
    })
    private Set<CasosEspeciais> casosEspeciais;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cidadao_beneficio", joinColumns = {
            @JoinColumn(name = "cidadao_id")}, inverseJoinColumns = {
            @JoinColumn(name = "beneficio_id")
    })
    private Set<Beneficio> beneficios;

}
