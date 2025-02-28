package com.example.demo.utils;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiscenteUtils {

    @Autowired
    private CorsoUtils corsoUtils;
    @Autowired
    private CorsoRepository corsoRepository;

    public DiscenteDTO convertToDTO(Discente discente){
        DiscenteDTO discenteDTO = new DiscenteDTO();
        discenteDTO.setId(discente.getId());
        discenteDTO.setNome(discente.getNome());
        discenteDTO.setCognome(discente.getCognome());
        discenteDTO.setMatricola(discente.getMatricola());
        discenteDTO.setDataNascita(discente.getDataNascita());
        if(discente.getListaCorsi() != null){
            for(Corso corso: discente.getListaCorsi()){
                discenteDTO.getListaCorsiDTO().add(corso.getIdCorso());
            }
            return discenteDTO;
        }
        return discenteDTO;
    }

    public Discente convertToEntity(DiscenteDTO discenteDTO){
        Discente discente = new Discente();
        discente.setId(discenteDTO.getId());
        discente.setNome(discenteDTO.getNome());
        discente.setCognome(discenteDTO.getCognome());
        discente.setMatricola(discenteDTO.getMatricola());
        discente.setDataNascita(discenteDTO.getDataNascita());
        if(discenteDTO.getListaCorsiDTO() != null){
            List<Corso> lCorso = corsoRepository.findAllById(discenteDTO.getListaCorsiDTO());
            for(Corso corso : lCorso){
                corso.getListaDiscenti().add(discente);
            }
            return discente;
        }
        return discente;
    }

/*    public DiscenteDTO convertToDTOConCorso(Discente discente, Corso corso){
        DiscenteDTO discenteDTO = new DiscenteDTO();
        discenteDTO.setId(discente.getId());
        discenteDTO.setNome(discente.getNome());
        discenteDTO.setCognome(discente.getCognome());
        discenteDTO.setMatricola(discente.getMatricola());
        discenteDTO.setDataNascita(discente.getDataNascita());
        CorsoDTO corsoDTO = corsoUtils.corsotoDTO(corso);
        discenteDTO.getListaCorsiDTO().add(corsoDTO);
        return discenteDTO;
    }*/
}
