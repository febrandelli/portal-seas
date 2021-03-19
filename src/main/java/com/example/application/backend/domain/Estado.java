package com.example.application.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ESTADOS")
public class Estado {

    @Id
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "UF")
    private String uF;
}
