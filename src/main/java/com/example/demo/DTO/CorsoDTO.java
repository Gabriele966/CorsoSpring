package com.example.demo.DTO;

import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class CorsoDTO {
    private Integer idCorso;
    private String nomeCorso;
    private LocalDate dataInizio;
    private String durata;
    private DocenteDTO docente;
    private List<DiscenteDTO> discente;

    public List<DiscenteDTO> getDiscenteDTO() {
        return discente;
    }

    public void setDiscenteDTO(List<DiscenteDTO> discente) {
        this.discente = discente;
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
