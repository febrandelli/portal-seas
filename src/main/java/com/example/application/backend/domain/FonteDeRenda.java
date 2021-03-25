package com.example.application.backend.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "FONTE_RENDA")
public class FonteDeRenda {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String nomeclatura;

    @Override
    public String toString(){
        return this.nomeclatura;
    }
}
