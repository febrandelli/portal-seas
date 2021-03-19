package com.example.application.backend.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AbordadoMotivosKey implements Serializable {

    @Column(name = "abordado_id")
    private Integer abordadoId;

    @Column(name = "motivo_id")
    private Integer motivoId;
}
