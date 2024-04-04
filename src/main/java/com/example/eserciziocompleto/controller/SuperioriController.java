package com.example.eserciziocompleto.controller;

import com.example.eserciziocompleto.entity.Superiori;
import com.example.eserciziocompleto.service.SuperioriService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/superiori")
public class SuperioriController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SuperioriService superioriService;

    public SuperioriController(SuperioriService superioriService) {
        this.superioriService = superioriService;
    }


    @PostMapping(value = "")
    public ResponseEntity<Superiori> create(@RequestBody Superiori superiori) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(superioriService.create(superiori));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
        }
    }


    @GetMapping(value = "")
    public ResponseEntity<List<Superiori>> getAll() {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(superioriService.getAll());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }


    @GetMapping("/{id}")
    public ResponseEntity<Superiori> getById(@PathVariable Long id) {
        try {
            Superiori superiori = superioriService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(superiori);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Superiori> update(@PathVariable Long id, @RequestBody Superiori updatedSuperiori) {
        try {
            Superiori existingSuperiori = superioriService.getById(id);
            if (existingSuperiori == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            existingSuperiori.setNamePadroni(updatedSuperiori.getNamePadroni());
            existingSuperiori.setSurnamePadroni(updatedSuperiori.getSurnamePadroni());
            existingSuperiori.setBirth(updatedSuperiori.getBirth());
            existingSuperiori.setEMail(updatedSuperiori.getEMail());
            Superiori updatedEntity = superioriService.create(existingSuperiori);
            return ResponseEntity.status(HttpStatus.OK).body(updatedEntity);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            Superiori existingSuperiori = superioriService.getById(id);
            if (existingSuperiori == null) {
                return ResponseEntity.notFound().build();
            }
            superioriService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}