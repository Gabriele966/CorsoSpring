package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "corsotest")
public class Corso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCorso;
    @Column(name = "nome_corso")
    private String nomeCorso;
    @Column(name = "data_inizio")
    private LocalDate dataInizio;
    @Column(name = "durata")
    private String durata;
    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "classecorso",
            joinColumns = @JoinColumn(name = "id_corso"),
            inverseJoinColumns = @JoinColumn(name = "id_discente")
    )
    private List<Discente> listaDiscenti = new ArrayList<Discente>();


    public List<Discente> getListaDiscenti() {
        return listaDiscenti;
    }

    public void setListaDiscenti(List<Discente> listaDiscenti) {
        this.listaDiscenti = listaDiscenti;
    }

    public String getNomeCorso() {return nomeCorso;}
    public void setNomeCorso(String nomeCorso) {this.nomeCorso = nomeCorso; }

    public LocalDate getDataInizio() {return dataInizio;}
    public void setDataInizio(LocalDate dataInizio) {this.dataInizio = dataInizio;}

    public String getDurata() {return durata;}
    public void setDurata(String durata) {this.durata = durata;}

    public int getIdCorso() {return idCorso;}
    public void setIdCorso(Integer idCorso) {this.idCorso = idCorso;}

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }
}
