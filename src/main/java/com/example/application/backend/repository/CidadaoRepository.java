package com.example.application.backend.repository;

import com.example.application.backend.domain.Cidadao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadaoRepository extends JpaRepository<Cidadao,Long>{
}
