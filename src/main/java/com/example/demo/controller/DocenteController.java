package com.example.demo.controller;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docente")
public class DocenteController {

//    @Autowired
//    DocenteService docenteService;

    private final DocenteService docenteService;

    public DocenteController(DocenteService docenteService) {
        this.docenteService = docenteService;
    }

    @GetMapping("/getDocenteById/{idDocente}")
    public DocenteDTO getDocenteById(@PathVariable ("idDocente" )Integer id) {
        return docenteService.getDocenteById(id);
    }

    @PostMapping("/insert")
    public DocenteDTO createDocente(@RequestBody DocenteDTO docenteDTO) {
            return docenteService.insert(docenteDTO);
    }

    @DeleteMapping("/delete/{idDocente}")
    public DocenteDTO  deleteDocenteById(@PathVariable ("idDocente" )Integer id) {
        return docenteService.delete(id);
    }

    @PostMapping("/getAll")
    public List<DocenteDTO> getAllDocentes() {
        return docenteService.getAllDocentes();
    }

    @PutMapping("/update/{idDocente}")
    public DocenteDTO update(@PathVariable("idDocente") Integer id, @RequestBody DocenteDTO docenteDTO){
        return docenteService.update(id, docenteDTO);
    }

}
