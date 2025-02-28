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
    private final DiscenteRepository discenteRepository;
    private final CorsoRepository corsoRepository;
    private final DiscenteUtils discenteUtils;

    private DiscenteService(DiscenteRepository discenteRepository, CorsoRepository corsoRepository, DiscenteUtils discenteUtils) {
        this.discenteRepository = discenteRepository;
        this.corsoRepository = corsoRepository;
        this.discenteUtils = discenteUtils;
    }

    public DiscenteDTO getDiscnteById(Integer id){
        Optional<Discente> discente = discenteRepository.findById(id);
        if(discente.isPresent()){
            DiscenteDTO discenteDTO = discenteUtils.convertToDTO(discente.get());
            return discenteDTO;
        }else{
            throw new EntityNotFoundException();
        }
    }

    public DiscenteDTO insert(DiscenteDTO discenteDTO){
        Discente discente = discenteUtils.convertToEntity(discenteDTO);
        Discente oDiscnete = discenteRepository.save(discente);
        return discenteUtils.convertToDTO(oDiscnete);
    }

    public DiscenteDTO delete(Integer id){
        Optional<Discente> discente = discenteRepository.findById(id);
        if(discente.isPresent()){
            List<Corso> lCorso = discente.get().getListaCorsi();
            for(int i = 0; i < lCorso.size(); i++){
                lCorso.get(i).getIdCorso();
                Optional <Corso> corso = corsoRepository.findById(lCorso.get(i).getIdCorso());
                corso.get().getListaDiscenti().remove(discente.get());
                corsoRepository.save(corso.get());
            }
            discente.get().getListaCorsi().clear();
            DiscenteDTO docenteDTO = discenteUtils.convertToDTO(discente.get());
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
            DiscenteDTO discenteDTO = discenteUtils.convertToDTO(lDiscnete.get(i));
            lDiscenteDTO.add(discenteDTO);
        }
        return lDiscenteDTO;
    }


    public DiscenteDTO update(Integer id, DiscenteDTO discenteDTO){
        Optional<Discente> discente = discenteRepository.findById(id);
        if(discente.isPresent()){
            discenteDTO.setId(id);
            Discente oDiscente = discenteUtils.convertToEntity(discenteDTO);
            discenteRepository.save(oDiscente);
            return discenteUtils.convertToDTO(oDiscente);
        }else{
            throw new EntityNotFoundException();
        }
    }


   /* public DiscenteDTO discneteToCorso(Integer idCorso, Integer idDiscente){
        Optional <Corso> corso = corsoRepository.findById(idCorso);
        Optional <Discente> discente = discenteRepository.findById(idDiscente);
        if(corso.isPresent() && discente.isPresent()){
            corso.get().getListaDiscenti().add(discente.get());
            if (discente.get().getListaCorsi().contains(corso.get())){
                throw new EntityNotFoundException("Entità già presente nel databse");
            }else{
                discente.get().getListaCorsi().add(corso.get());
                discenteRepository.save(discente.get());
                corsoRepository.save(corso.get());
                DiscenteDTO discenteDTO = discenteUtils.convertToDTOConCorso(discente.get(), corso.get());
                return discenteDTO;
            }
        }else{
            throw new EntityNotFoundException();
        }
    }*/

}
