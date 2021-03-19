package com.example.application.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MOTIVOS")
public class Motivo {

    @Id
    private Integer id;

    @Column(name = "DESCRICAO")
    private String descricao;

}
