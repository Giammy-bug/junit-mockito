package com.example.eserciziocompleto.repository;


import com.example.eserciziocompleto.entity.Superiori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperioriRepository extends JpaRepository<Superiori, Long> {
}
