package com.example.demo.controller;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.entity.Corso;
import com.example.demo.service.CorsoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corso")
public class CorsoController {
    public final CorsoService corsoService;
    public CorsoController(CorsoService corsoService) {
        this.corsoService = corsoService;
    }

    @GetMapping("/getCorsoById/{idCorso}")
    public CorsoDTO getCorsoById(@PathVariable ("idCorso") Integer id) {
        return corsoService.getCorsoById(id);
    }

    @PostMapping("/insert")
    public CorsoDTO insert(@RequestBody CorsoDTO corsoDTO) {
        return corsoService.insert(corsoDTO);
    }

    @DeleteMapping("/delete/{idCorso}")
    public CorsoDTO delete(@PathVariable ("idCorso") Integer id) {
        return corsoService.delete(id);
    }

    @GetMapping("/getAll")
    public List<CorsoDTO> getAll() {
        return corsoService.getAll();
    }

    @PostMapping("/update/{idCorso}")
    public CorsoDTO update(@PathVariable("idCorso") Integer id , @RequestBody CorsoDTO corsoDTO) {
        return corsoService.update(id, corsoDTO);
    }

    @DeleteMapping("delete/{idDiscente}/to/{idCorso}")
    public CorsoDTO deleteDiscenteToCorso(@PathVariable("idCorso") Integer idCorso, @PathVariable("idDiscente") Integer idDiscente) {
        return corsoService.deleteDiscenteToCorso(idCorso, idDiscente);
    }

}
