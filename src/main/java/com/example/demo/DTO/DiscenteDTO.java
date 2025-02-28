package com.example.demo.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiscenteDTO {

    private Integer id;
    private String nome;
    private String cognome;
    private Integer matricola ;
    private LocalDate dataNascita;
    private List<Integer> corsi = new ArrayList<Integer>();

    public List<Integer> getListaCorsiDTO() {
        return corsi;
    }

    public void setListaCorsiDTO(List<Integer> corsi) {
        this.corsi = corsi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getMatricola() {
        return matricola;
    }

    public void setMatricola(Integer matricola) {
        this.matricola = matricola;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }


}
