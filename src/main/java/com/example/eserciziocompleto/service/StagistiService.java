package com.example.eserciziocompleto.service;


import com.example.eserciziocompleto.entity.Stagisti;
import com.example.eserciziocompleto.repository.StagistiRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StagistiService {


    private final StagistiRepository stagistiRepository;

    public StagistiService(StagistiRepository stagistiRepository) {
        this.stagistiRepository = stagistiRepository;
    }


    @Transactional
    public Stagisti create(Stagisti stagisti) {
        return stagistiRepository.save(stagisti);
    }

    public Stagisti getById(Long id) {
        return stagistiRepository.findById(id).orElse(null);
    }

    public List<Stagisti> getAll() {
        return stagistiRepository.findAll();
    }

    @Transactional
    public Stagisti update(Stagisti stagisti) {
        if (stagistiRepository.existsById(stagisti.getId())) {
            return stagistiRepository.save(stagisti);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        stagistiRepository.deleteById(id);
    }


}
