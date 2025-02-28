package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "docentetest")
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cognome;
    @OneToMany(mappedBy = "docente",  cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Corso> listaCorsi;

    public List<Corso> getListaCorsi() {return listaCorsi;}
    public void setListaCorsi(List<Corso> listaCorsi) {this.listaCorsi = listaCorsi;}

    public void setNome(String nome) {this.nome = nome;}
    public String getNome() {
        return nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getCognome() {
        return cognome;
    }

    public void setid(Integer id) {
        this.id = id;
    }
    public Integer getid() {
        return id;
    }
}
