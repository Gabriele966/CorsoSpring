package com.example.demo.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CorsoDTO {
    private Integer idCorso;
    private String nomeCorso;
    private LocalDate dataInizio;
    private String durata;
    private DocenteDTO docente;
    private List<Integer> listaDiscente = new ArrayList<>();

    public List<Integer> getListaDiscenteDTO() {
        return listaDiscente;
    }

    public void setListaDiscenteDTO(List<Integer> discente) {
        this.listaDiscente = discente;
    }

    public Integer getIdCorso() {
        return idCorso;
    }

    public void setIdCorso(Integer idCorso) {
        this.idCorso = idCorso;
    }

    public DocenteDTO getDocenteDTO() {
        return docente;
    }

    public void setDocenteDTO(DocenteDTO docente) {
        this.docente = docente;
    }

    public String getNomeCorso() {
        return nomeCorso;
    }
    public void setNomeCorso(String nomeCorso) {
        this.nomeCorso = nomeCorso;
    }
    public LocalDate getDataInizio() {
        return dataInizio;
    }
    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }
    public String getDurata() {
        return durata;
    }
    public void setDurata(String durata) {
        this.durata = durata;
    }


}
