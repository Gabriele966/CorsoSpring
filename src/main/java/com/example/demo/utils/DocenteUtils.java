package com.example.demo.utils;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DocenteUtils {

    @Autowired
    private CorsoRepository corsoRepository;

    public DocenteDTO DocenteToDTO(Docente docente) {
        DocenteDTO docenteDTO = new DocenteDTO();
        List<Integer> listaIdCorsi = new ArrayList<>();
        docenteDTO.setId(docente.getid());
        docenteDTO.setNome(docente.getNome());
        docenteDTO.setCognome(docente.getCognome());
        if (docente.getListaCorsi() != null ) {
            for (int i = 0; i < docente.getListaCorsi().size(); i++) {
                listaIdCorsi.add(docente.getListaCorsi().get(i).getIdCorso());
                docenteDTO.setListaCorsi(listaIdCorsi);
            }
        } else {
            docente.getListaCorsi().clear();
        }
        return docenteDTO;
    }


    public Docente DTOToDocente(DocenteDTO docenteDTO) {
        Docente docente = new Docente();
        docente.setid(docenteDTO.getId());
        docente.setNome(docenteDTO.getNome());
        docente.setCognome(docenteDTO.getCognome());
        if (docenteDTO.getListaCorsi() != null) {
            List<Corso> lCorso = corsoRepository.findAllById(docenteDTO.getListaCorsi());
            for (Corso corso : lCorso) {
                corso.setDocente(docente);
            }
            docente.setListaCorsi(lCorso);
        } else {
            docente.setListaCorsi(new ArrayList<>());
        }
        return docente;
    }
}