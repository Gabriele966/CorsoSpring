package com.example.demo.utils;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import java.util.List;

public class DocenteConverter {

    public static DocenteDTO entityToDTO(Docente docente) {
       DocenteDTO docenteDTO = new DocenteDTO();
       docenteDTO.setId(docente.getid());
       docenteDTO.setNome(docente.getNome());
       docenteDTO.setCognome(docente.getCognome());
       List<CorsoDTO> listaCorsiDTO =  CorsoUtils.corsotoDTOPerDocente(docente.getListaCorsi());
       docenteDTO.setListaCorsi(listaCorsiDTO);
       return docenteDTO;
    }


    public static Docente DTOToEntity(DocenteDTO docenteDTO) {
        Docente docente = new Docente();
        docente.setid(docenteDTO.getId());
        docente.setNome(docenteDTO.getNome());
        docente.setCognome(docenteDTO.getCognome());
        List<Corso> listaCorso = CorsoUtils.DTOToCorsoPerDocente(docenteDTO.getListaCorsi());
        docente.setListaCorsi(listaCorso);
        return docente;
    }
}
