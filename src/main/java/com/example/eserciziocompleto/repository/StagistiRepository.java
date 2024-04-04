package com.example.eserciziocompleto.repository;

import com.example.eserciziocompleto.entity.Stagisti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StagistiRepository extends JpaRepository<Stagisti, Long> {
}
