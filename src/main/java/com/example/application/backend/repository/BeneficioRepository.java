package com.example.application.backend.repository;

import com.example.application.backend.domain.Beneficio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficioRepository extends JpaRepository<Beneficio, Integer> {
}
