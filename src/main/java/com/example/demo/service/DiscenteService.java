package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.utils.CorsoUtils;
import com.example.demo.utils.DiscenteUtils;
import com.example.demo.entity.Discente;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.utils.DiscenteUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.*;

import java.util.Optional;

@Service
public class DiscenteService {
    public final DiscenteRepository discenteRepository;
    public final CorsoRepository corsoRepository;


    public DiscenteService(DiscenteRepository discenteRepository, CorsoRepository corsoRepository) {
        this.discenteRepository = discenteRepository;
        this.corsoRepository = corsoRepository;
    }

    public DiscenteDTO getDiscnteById(Integer id){
        Optional<Discente> discente = discenteRepository.findById(id);
        if(discente.isPresent()){
            DiscenteDTO discenteDTO = DiscenteUtils.convertToDTO(discente.get());
            return discenteDTO;
        }else{
            throw new EntityNotFoundException();
        }
    }

    public DiscenteDTO insert(DiscenteDTO discenteDTO){
        Discente discente = DiscenteUtils.convertToEntity(discenteDTO);
        Discente oDiscnete = discenteRepository.save(discente);
        return DiscenteUtils.convertToDTO(oDiscnete);
    }

    public DiscenteDTO delete(Integer id){
        Optional<Discente> discente = discenteRepository.findById(id);
        if(discente.isPresent()){
            DiscenteDTO docenteDTO = DiscenteUtils.convertToDTO(discente.get());
            discenteRepository.deleteById(id);
            return docenteDTO;
        }else{
            throw new EntityNotFoundException();
        }
    }

    public List<DiscenteDTO> getAllDiscentes(){
        List<Discente> lDiscnete = discenteRepository.findAll();
        List<DiscenteDTO> lDiscenteDTO = new ArrayList<>();
        for(int i = 0; i<lDiscnete.size(); i++){
            DiscenteDTO discenteDTO = DiscenteUtils.convertToDTO(lDiscnete.get(i));
            lDiscenteDTO.add(discenteDTO);
        }
        return lDiscenteDTO;
    }


    public DiscenteDTO update(Integer id, DiscenteDTO discenteDTO){
        Optional<Discente> discente = discenteRepository.findById(id);
        if(discente.isPresent()){
            discenteDTO.setId(id);
            Discente oDiscente = DiscenteUtils.convertToEntity(discenteDTO);
            discenteRepository.save(oDiscente);
            return DiscenteUtils.convertToDTO(oDiscente);
        }else{
            throw new EntityNotFoundException();
        }
    }

    public DiscenteDTO discneteToCorso(Integer idCorso, Integer idDiscente){
        Optional <Corso> corso = corsoRepository.findById(idCorso);
        Optional <Discente> discente = discenteRepository.findById(idDiscente);
        if(corso.isPresent() && discente.isPresent()){
            corso.get().getListaDiscenti().add(discente.get());
            if (discente.get().listaCorsi.contains(corso.get())){
                throw new EntityNotFoundException("Entità già presente nel databse");
            }else{
                discente.get().getListaCorsi().add(corso.get());
                discenteRepository.save(discente.get());
                corsoRepository.save(corso.get());
                DiscenteDTO discenteDTO = DiscenteUtils.convertToDTOConCorso(discente.get(), corso.get());
                return discenteDTO;
            }
        }else{
            throw new EntityNotFoundException();
        }
    }

}
