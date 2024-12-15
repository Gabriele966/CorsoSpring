package com.example.demo.utils;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.utils.DocenteConverter;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class CorsoUtils {

    public static CorsoDTO corsotoDTO(Corso corso) {
            List<DiscenteDTO> listaDiscente = new ArrayList<>();
            CorsoDTO corsoDTO = new CorsoDTO();
            corsoDTO.setIdCorso(corso.getIdCorso());
            corsoDTO.setNomeCorso(corso.getNomeCorso());
            corsoDTO.setDataInizio(corso.getDataInizio());
            corsoDTO.setDurata(corso.getDurata());
            DocenteDTO docenteDTO = DocenteConverter.entityToDTO(corso.getDocente());
            docenteDTO.setListaCorsi(new ArrayList<>());
            corsoDTO.setDocenteDTO(docenteDTO);
            for(int i = 0; i<corso.getListaDiscenti().size(); i++){
                DiscenteDTO discenteDTO = DiscenteUtils.convertToDTO(corso.getListaDiscenti().get(i));
                listaDiscente.add(discenteDTO);
            }
        return corsoDTO;
    }

    public static Corso DTOToCorso(CorsoDTO corsoDTO) {
            List<Discente> lDiscente = new ArrayList<>();
            Corso corso = new Corso();
            corso.setIdCorso(corsoDTO.getIdCorso());
            corso.setNomeCorso(corsoDTO.getNomeCorso());
            corso.setDataInizio(corsoDTO.getDataInizio());
            corso.setDurata(corsoDTO.getDurata());
            Docente docente = DocenteConverter.DTOToEntity(corsoDTO.getDocenteDTO());
            corso.setDocente(docente);
            for(int i = 0; i< corsoDTO.getDiscenteDTO().size() ; i++){
                Discente discente = DiscenteUtils.convertToEntity(corsoDTO.getDiscenteDTO().get(i));
                lDiscente.add(discente);
            }
            return corso;
    }

    public static List<CorsoDTO> corsotoDTOPerDocente(List<Corso> listacorso) {
        List<CorsoDTO> listaCorsoDTO = new ArrayList<>();
        for(int i = 0; i < listacorso.size(); i++) {
            CorsoDTO corsoDTO = new CorsoDTO();
            corsoDTO.setIdCorso(listacorso.get(i).getIdCorso());
            corsoDTO.setNomeCorso(listacorso.get(i).getNomeCorso());
            corsoDTO.setDataInizio(listacorso.get(i).getDataInizio());
            corsoDTO.setDurata(listacorso.get(i).getDurata());
            listaCorsoDTO.add(corsoDTO);
        }
        return listaCorsoDTO;
    }

    public static List<Corso> DTOToCorsoPerDocente (List<CorsoDTO> listacorso) {
        List<Corso> listaCorso = new ArrayList<>();
        for(int i = 0; i < listacorso.size(); i++) {
            Corso corso = new Corso();
            corso.setIdCorso(listacorso.get(i).getIdCorso());
            corso.setNomeCorso(listacorso.get(i).getNomeCorso());
            corso.setDataInizio(listacorso.get(i).getDataInizio());
            corso.setDurata(listacorso.get(i).getDurata());
            listaCorso.add(corso);
        }
        return listaCorso;
    }


    public static CorsoDTO corsoToDTOPerDiscente(Corso corso) {
        CorsoDTO corsoDTO = new CorsoDTO();
        corsoDTO.setIdCorso(corso.getIdCorso());
        corsoDTO.setNomeCorso(corso.getNomeCorso());
        corsoDTO.setDataInizio(corso.getDataInizio());
        corsoDTO.setDurata(corso.getDurata());
        return corsoDTO;
    }
}
