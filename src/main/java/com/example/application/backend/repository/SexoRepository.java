package com.example.application.backend.repository;

import com.example.application.backend.domain.Sexo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SexoRepository extends JpaRepository<Sexo, Integer> {
}
