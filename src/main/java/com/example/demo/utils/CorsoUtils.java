package com.example.demo.utils;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CorsoUtils {

    @Autowired
    private DocenteUtils docenteUtils;
    @Autowired
    private DiscenteRepository discenteRepository;

    public CorsoDTO corsotoDTO(Corso corso) {
            CorsoDTO corsoDTO = new CorsoDTO();
            corsoDTO.setIdCorso(corso.getIdCorso());
            corsoDTO.setNomeCorso(corso.getNomeCorso());
            corsoDTO.setDataInizio(corso.getDataInizio());
            corsoDTO.setDurata(corso.getDurata());
            if (corso.getListaDiscenti() != null) {
                int i = corso.getListaDiscenti().size();
                for(Discente discente : corso.getListaDiscenti() ){
                    corsoDTO.getListaDiscenteDTO().add(discente.getId());
                }
            }
            if(corso.getDocente() != null) {
                DocenteDTO docenteDTO = docenteUtils.DocenteToDTO(corso.getDocente());
                corsoDTO.setDocenteDTO(docenteDTO);
            }else{
                DocenteDTO docenteDTO = new DocenteDTO();
                docenteDTO.setId(0);
                corsoDTO.setDocenteDTO(docenteDTO);
            }

        return corsoDTO;
    }

    public Corso DTOToCorso(CorsoDTO corsoDTO) {
            Corso corso = new Corso();
            corso.setIdCorso(corsoDTO.getIdCorso());
            corso.setNomeCorso(corsoDTO.getNomeCorso());
            corso.setDataInizio(corsoDTO.getDataInizio());
            corso.setDurata(corsoDTO.getDurata());
            Docente docente;
            if(corsoDTO.getDocenteDTO() != null){
                docente = docenteUtils.DTOToDocente(corsoDTO.getDocenteDTO());
            }else{
                docente = new Docente();
                docente.setid(0);
            }
            if(corsoDTO.getListaDiscenteDTO() != null){
                List<Discente> lDiscenti = discenteRepository.findAllById(corsoDTO.getListaDiscenteDTO());
                corso.setListaDiscenti(lDiscenti);
                for(Discente discente : lDiscenti){
                    discente.getListaCorsi().add(corso);
                }
            }
            corso.setDocente(docente);
            return corso;
    }


    public  CorsoDTO corsoToDTOPerDiscente(Corso corso) {
        CorsoDTO corsoDTO = new CorsoDTO();
        corsoDTO.setIdCorso(corso.getIdCorso());
        corsoDTO.setNomeCorso(corso.getNomeCorso());
        corsoDTO.setDataInizio(corso.getDataInizio());
        corsoDTO.setDurata(corso.getDurata());
        return corsoDTO;
    }
}



  /*  public List<CorsoDTO> corsotoDTOPerDocente(List<Corso> listacorso) {
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

    public List<Corso> DTOToCorsoPerDocente (List<CorsoDTO> listacorso) {
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
*/

