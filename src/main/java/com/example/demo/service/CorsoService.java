package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.utils.CorsoUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CorsoService {
    public final CorsoRepository corsoRepository;

    public CorsoService(CorsoRepository corsoRepository) {
        this.corsoRepository = corsoRepository;
    }

    public CorsoDTO getCorsoById(Integer id) {
        Optional<Corso> corso = corsoRepository.findById(id);
        if (corso.isPresent()) {
            CorsoDTO corsoDTO = CorsoUtils.corsotoDTO(corso.get());
            return corsoDTO;
        }else{
            throw new EntityNotFoundException();
        }
    }

    public CorsoDTO insert(CorsoDTO corsoDTO) {
        Corso corso = CorsoUtils.DTOToCorso(corsoDTO);
        corso = corsoRepository.save(corso);
        return CorsoUtils.corsotoDTO(corso);
    }

    public CorsoDTO delete(Integer id) {
        Optional<Corso> corso = corsoRepository.findById(id);
        if(corso.isPresent()) {
            CorsoDTO corsoDTO = CorsoUtils.corsotoDTO(corso.get());
            corsoRepository.deleteById(id);
            return corsoDTO;
        }else{
            throw new EntityNotFoundException();
        }
    }

    public List<CorsoDTO> getAll() {
        List<Corso> lCorso = corsoRepository.findAll();
        List<CorsoDTO> lCorsoDTO = new ArrayList<>();
        for (Corso corso : lCorso) {
            CorsoDTO corsoDTO = CorsoUtils.corsotoDTO(corso);
            lCorsoDTO.add(corsoDTO);
        }
        return lCorsoDTO;
    }

    public CorsoDTO update(Integer id, CorsoDTO corsoDTO){
        Optional<Corso> corso = corsoRepository.findById(id);
        if(corso.isPresent()) {
            corsoDTO.setIdCorso(id);
            Corso oCorso = CorsoUtils.DTOToCorso(corsoDTO);
            corsoRepository.save(oCorso);
            return CorsoUtils.corsotoDTO(corso.get());
        }else{
            throw new EntityNotFoundException();
        }
    }

}
