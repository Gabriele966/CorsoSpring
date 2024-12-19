package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "discentetest")
public class Discente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cognome;
    private int matricola ;
    private LocalDate dataNascita;
    @ManyToMany(mappedBy = "listaDiscenti")
    private List<Corso> listaCorsi;

    public List<Corso> getListaCorsi() {
        return listaCorsi;
    }

    public void setListaCorsi(List<Corso> listaCorsi) {
        this.listaCorsi = listaCorsi;
    }

    //Getter e Setter id
    public int getId() {return id;}
    public void setId(Integer id) { this.id = id;}

    //Getter e Setter nome
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    //Getter e Setter Cognome
    public String getCognome() {return cognome;}
    public void setCognome(String cognome) {this.cognome = cognome;}

    //Getter e Setter matricola
    public int getMatricola() {return matricola;}
    public void setMatricola(int matricola) {this.matricola = matricola;}

    //Getter e Setter Data
    public LocalDate getDataNascita() {return dataNascita;}
    public void setDataNascita(LocalDate dataNascita) {this.dataNascita = dataNascita;}
}
