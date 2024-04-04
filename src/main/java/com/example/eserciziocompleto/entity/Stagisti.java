package com.example.eserciziocompleto.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "stagisti")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Stagisti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name_schiavi")
    private String nameSchiavi;
    @Column(name = "surname_schiavi")
    private String surnameSchiavi;
    @Column(name = "birth")
    private Date birth;
    @Column(name = "email")
    private String eMail;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "stagisti_superiori",
            joinColumns = @JoinColumn(name = "stagisti_id"),
            inverseJoinColumns = @JoinColumn(name = "superiori_id")
    )
    private List<Superiori> superiori = new ArrayList<>();
}// test tramite introspector e reflection