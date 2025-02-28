package com.example.demo.service;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.utils.DocenteUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class DocenteService {
//    @Autowired
//    DocenteRepository docenteRepository;
    private final DocenteRepository docenteRepository;
    private final DiscenteRepository discenteRepository;
    private final CorsoRepository corsoRepository;
    private final DocenteUtils docenteUtils;

    public DocenteService(DocenteRepository docenteRepository, DiscenteRepository discenteRepository, CorsoRepository corsoRepository, DocenteUtils docenteUtils) {
        this.corsoRepository = corsoRepository;
        this.docenteRepository = docenteRepository;
        this.discenteRepository = discenteRepository;
        this.docenteUtils = docenteUtils;
    }

    public DocenteDTO getDocenteById(Integer id) {
        Optional<Docente> docente = docenteRepository.findById(id);
        if(docente.isPresent()) {
            DocenteDTO docenteDTO = docenteUtils.DocenteToDTO(docente.get());
            return docenteDTO;
        }else{
                throw new EntityNotFoundException();
        }
    }

    public DocenteDTO insert(DocenteDTO docente) {
            Docente docenteSaved  = docenteRepository.save(docenteUtils.DTOToDocente(docente));
            return docenteUtils.DocenteToDTO(docenteSaved);
    }

    public DocenteDTO delete(Integer id) {
        Optional<Docente> docente = docenteRepository.findById(id);
        if(docente.isPresent()) {
            for(int i = 0; i < docente.get().getListaCorsi().size(); i++) {
                docente.get().getListaCorsi().get(i).setDocente(null);
            }
            docente.get().getListaCorsi().clear();
            DocenteDTO docenteDTO = docenteUtils.DocenteToDTO(docente.get());
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
            lDocenteDTO.add(docenteUtils.DocenteToDTO(lDocente.get(i)));
            System.out.println(lDocenteDTO.get(i));
        }
        return lDocenteDTO;
    }

    public DocenteDTO update(Integer id, DocenteDTO docenteDTO) {
        Optional<Docente> docente = docenteRepository.findById(id);
        if(docente.isPresent()) {
            docenteDTO.setId(id);
            Docente docenteSaved = docenteUtils.DTOToDocente(docenteDTO);
            docenteRepository.save(docenteSaved);
            return docenteUtils.DocenteToDTO(docenteSaved);
        }else{
            throw new EntityNotFoundException();
        }
    }
}
