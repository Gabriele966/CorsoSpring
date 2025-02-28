package com.example.demo.DTO;

import com.example.demo.entity.Corso;
import jakarta.persistence.OneToMany;

import java.util.List;

public class DocenteDTO {
    private Integer id;
    private String nome;
    private String cognome;
    private List<Integer> listaCorsi;

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getCognome() {return cognome;}
    public void setCognome(String cognome) {this.cognome = cognome;}

    public List<Integer> getListaCorsi() {return listaCorsi;}
    public void setListaCorsi(List<Integer> listaCorsi) {this.listaCorsi = listaCorsi;}
}
