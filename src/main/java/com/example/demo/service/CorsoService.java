package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.utils.CorsoUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CorsoService {
    private final CorsoRepository corsoRepository;
    private final DiscenteRepository discenteRepository;
    private final CorsoUtils corsoUtils;

    private CorsoService(CorsoRepository corsoRepository, DiscenteRepository discenteRepository, CorsoUtils corsoUtils) {
        this.corsoRepository = corsoRepository;
        this.discenteRepository = discenteRepository;
        this.corsoUtils = corsoUtils;
    }

    public CorsoDTO getCorsoById(Integer id) {
        Optional<Corso> corso = corsoRepository.findById(id);
        if (corso.isPresent()) {
            CorsoDTO corsoDTO = corsoUtils.corsotoDTO(corso.get());
            return corsoDTO;
        }else{
            throw new EntityNotFoundException();
        }
    }

    public CorsoDTO insert(CorsoDTO corsoDTO) {
        Corso corso = corsoUtils.DTOToCorso(corsoDTO);
        corso = corsoRepository.save(corso);
        return corsoUtils.corsotoDTO(corso);
    }

    public CorsoDTO delete(Integer id) {
        Optional<Corso> corso = corsoRepository.findById(id);
        if(corso.isPresent()) {
            List<Discente> lDiscente = corso.get().getListaDiscenti();
            for(int i = 0; i < lDiscente.size(); i++){
                Optional<Discente> discente = discenteRepository.findById(lDiscente.get(i).getId());
                discente.get().getListaCorsi().remove(corso.get());
            }
            corso.get().getListaDiscenti().clear();
            CorsoDTO corsoDTO = corsoUtils.corsotoDTO(corso.get());
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
            CorsoDTO corsoDTO = corsoUtils.corsotoDTO(corso);
            lCorsoDTO.add(corsoDTO);
        }
        return lCorsoDTO;
    }

    public CorsoDTO update(Integer id, CorsoDTO corsoDTO){
        Optional<Corso> corso = corsoRepository.findById(id);
        if(corso.isPresent()) {
            corsoDTO.setIdCorso(id);
            Corso oCorso = corsoUtils.DTOToCorso(corsoDTO);
            corsoRepository.save(oCorso);
            return corsoUtils.corsotoDTO(corso.get());
        }else{
            throw new EntityNotFoundException();
        }
    }

    public CorsoDTO deleteDiscenteToCorso(Integer idCroso, Integer idDiscente){
        Optional<Corso> corso = corsoRepository.findById(idCroso);
        Optional<Discente> discente = discenteRepository.findById(idDiscente);
        if(corso.isPresent() && discente.isPresent()){
            if(corso.get().getListaDiscenti().contains(discente.get())){
                corso.get().getListaDiscenti().remove(discente.get());
                discente.get().getListaCorsi().remove(corso.get());
                corsoRepository.save(corso.get());
                discenteRepository.save(discente.get());
            }
            return corsoUtils.corsotoDTO(corso.get());
        }else{
            throw new EntityNotFoundException();
        }
    }

    public void  popolaClasse(Integer idCorso, Integer idDiscente){
       Optional<Corso> corso = corsoRepository.findById(idCorso);
       Optional<Discente> discente = discenteRepository.findById(idDiscente);
       if(corso.isPresent() && discente.isPresent()){
           corso.get().getListaDiscenti().add(discente.get());
           discente.get().getListaCorsi().add(corso.get());
           discenteRepository.save(discente.get());
           corsoRepository.save(corso.get());
       }else{
           throw new EntityNotFoundException();
       }
    }




}
