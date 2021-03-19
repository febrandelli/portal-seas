package com.example.application.backend.repository;

import com.example.application.backend.domain.Motivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotivoRepository extends JpaRepository<Motivo, Integer> {
}
