package com.example.eserciziocompleto.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "superiori")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Superiori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name_padroni")
    private String namePadroni;
    @Column(name = "surname_padroni")
    private String surnamePadroni;
    @Column(name = "birth")
    private Date birth;
    @Column(name = "email")
    private String eMail;
    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "superiori")
    private List<Stagisti> stagisti = new ArrayList<>();
}// test tramite introspector e reflection
