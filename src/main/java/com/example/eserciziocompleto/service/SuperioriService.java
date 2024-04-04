package com.example.eserciziocompleto.service;


import com.example.eserciziocompleto.entity.Superiori;
import com.example.eserciziocompleto.repository.SuperioriRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SuperioriService {


    private final SuperioriRepository superioriRepository;

    public SuperioriService(SuperioriRepository superioriRepository) {
        this.superioriRepository = superioriRepository;
    }

    @Transactional
    public Superiori create(Superiori superiori) {
        return superioriRepository.save(superiori);
    }

    public Superiori getById(Long id) {
        return superioriRepository.findById(id).orElse(null);
    }

    public List<Superiori> getAll() {
        return superioriRepository.findAll();
    }

    @Transactional
    public Superiori update(Superiori superiori) {
        if (superioriRepository.existsById(superiori.getId())) {
            return superioriRepository.save(superiori);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        superioriRepository.deleteById(id);
    }

}
