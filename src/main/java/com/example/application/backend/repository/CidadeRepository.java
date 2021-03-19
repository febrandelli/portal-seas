package com.example.application.backend.repository;

import com.example.application.backend.domain.Cidade;
import com.example.application.backend.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    Optional<List<Cidade>> findByIdEstadoEquals(Estado idEstado);
}
