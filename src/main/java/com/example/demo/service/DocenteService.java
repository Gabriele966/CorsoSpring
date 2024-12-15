package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.utils.CorsoUtils;
import com.example.demo.utils.DocenteConverter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {
    private final DocenteRepository docenteRepository;

    public DocenteService(DocenteRepository docenteRepository) {
        this.docenteRepository = docenteRepository;
    }

    public DocenteDTO getDocenteById(Integer id) {
        Optional<Docente> docente = docenteRepository.findById(id);
        if(docente.isPresent()) {
            DocenteDTO docenteDTO = DocenteConverter.entityToDTO(docente.get());
            return docenteDTO;
        }else{
                throw new EntityNotFoundException();
        }
    }

    public DocenteDTO insert(DocenteDTO docente) {
        Docente docenteSaved  = docenteRepository.save(DocenteConverter.DTOToEntity(docente));
        return DocenteConverter.entityToDTO(docenteSaved);
    }

    public DocenteDTO delete(Integer id) {
        Optional<Docente> docente = docenteRepository.findById(id);
        if(docente.isPresent()) {
            DocenteDTO docenteDTO = DocenteConverter.entityToDTO(docente.get());
            docenteRepository.deleteById(id);
            return docenteDTO;
        }else{
            throw new EntityNotFoundException();
        }
    }

    public List<DocenteDTO> getAllDocentes() {
        List<Docente> lDocente = docenteRepository.findAll();
        List<DocenteDTO> lDocenteDTO = new ArrayList<>();
        for(int i =0; i < lDocente.size(); i++) {
            lDocenteDTO.add(DocenteConverter.entityToDTO(lDocente.get(i)));
            System.out.println(lDocenteDTO.get(i));
        }
        return lDocenteDTO;
    }

    public DocenteDTO update(Integer id, DocenteDTO docenteDTO) {
        Optional<Docente> docente = docenteRepository.findById(id);
        if(docente.isPresent()) {
            docenteDTO.setId(id);
            Docente docenteSaved = DocenteConverter.DTOToEntity(docenteDTO);
            docenteRepository.save(docenteSaved);
            return DocenteConverter.entityToDTO(docenteSaved);
        }else{
            throw new EntityNotFoundException();
        }
    }
}
