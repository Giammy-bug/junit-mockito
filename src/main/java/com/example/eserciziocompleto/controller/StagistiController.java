package com.example.eserciziocompleto.controller;

import com.example.eserciziocompleto.entity.Stagisti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.eserciziocompleto.service.StagistiService;


import java.util.List;


@RestController
@RequestMapping("/stagisti")
public class StagistiController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    private final StagistiService stagistiService;

    public StagistiController(StagistiService stagistiService) {
        this.stagistiService = stagistiService;
    }


    @PostMapping
    public ResponseEntity<Stagisti> create(@RequestBody Stagisti stagisti) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(stagistiService.create(stagisti));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
        }
    }


    @GetMapping
    public ResponseEntity<List<Stagisti>> getAll() {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(stagistiService.getAll());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }


    @GetMapping("/{id}")
    public ResponseEntity<Stagisti> getById(@PathVariable Long id) {
        try {
            Stagisti stagisti = stagistiService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(stagisti);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Stagisti> update(@PathVariable Long id, @RequestBody Stagisti updatedStagisti) {
        try {
            Stagisti existingStagisti = stagistiService.getById(id);
            if (existingStagisti == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            existingStagisti.setNameSchiavi(updatedStagisti.getNameSchiavi());
            existingStagisti.setSurnameSchiavi(updatedStagisti.getSurnameSchiavi());
            existingStagisti.setBirth(updatedStagisti.getBirth());
            existingStagisti.setEMail(updatedStagisti.getEMail());
            Stagisti updatedEntity = stagistiService.create(existingStagisti);
            return ResponseEntity.status(HttpStatus.OK).body(updatedEntity);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }//vedere come si comporta con un update in cascata, con oggetto padre che modifica il figlio


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            Stagisti existingStagisti = stagistiService.getById(id);
            if (existingStagisti == null) {
                return ResponseEntity.notFound().build();
            }
            stagistiService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
