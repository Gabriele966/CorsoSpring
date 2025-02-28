package com.example.demo.controller;


import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Discente;
import com.example.demo.service.DiscenteService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/discente")
public class DiscenteController {
    private final DiscenteService discenteService;

    public DiscenteController(DiscenteService discenteService) {
        this.discenteService = discenteService;
    }



    @GetMapping("/getDiscenteById/{idDiscente}")
    public DiscenteDTO getDiscenteById(@PathVariable ("idDiscente") Integer id) {
        return discenteService.getDiscnteById(id);
    }

    @PostMapping("/insert")
    public DiscenteDTO insert(@RequestBody DiscenteDTO discenteDTO){
        return discenteService.insert(discenteDTO);
    }


    @DeleteMapping("/delete/{IdDiscente}")
    public DiscenteDTO delete(@PathVariable ("IdDiscente") Integer IdDiscente){
        return discenteService.delete(IdDiscente);
    }

    @GetMapping("getAll")
    public List<DiscenteDTO> getAllDiscentes(){
        return discenteService.getAllDiscentes();
    }

    @PutMapping("/update/{idDocente}")
    public DiscenteDTO update(@PathVariable("idDocente")Integer id, @RequestBody DiscenteDTO discenteDTO){
        return discenteService.update(id, discenteDTO);
    }

    /*@PostMapping("{idCorso}/insert/{idDiscente}")
    public DiscenteDTO discneteToCorso(@PathVariable("idCorso") Integer idCorso, @PathVariable("idDiscente") Integer idDiscnete){
        return discenteService.discneteToCorso(idCorso, idDiscnete);
    }
*/
    

}
