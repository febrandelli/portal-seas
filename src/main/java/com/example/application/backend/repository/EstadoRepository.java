package com.example.application.backend.repository;

import com.example.application.backend.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    Optional<Estado> findByNome (String nomeEstado);
}
