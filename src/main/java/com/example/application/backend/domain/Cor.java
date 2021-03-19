package com.example.application.backend.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cor {

    @Id
    private Integer id;
    private String nomeclatura;
}
