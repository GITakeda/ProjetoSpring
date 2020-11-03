package com.example.demo.controller;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    MateriaService materiaService;

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTO> findById(@PathVariable Long id){
//        return materiaService.findById(id).map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
        return ResponseEntity.ok(materiaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<MateriaDTO>> findAll(){
        return ResponseEntity.ok(materiaService.findAll());
    }

    @PostMapping("/post")
    public ResponseEntity<Boolean> save(@RequestBody MateriaDTO materiaDTO){
        return ResponseEntity.created(URI.create("/materia/" + materiaService.save(materiaDTO))).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> save(@RequestBody MateriaDTO materiaDTO, @PathVariable Long id){
        Long idRegistro = 0l;

        idRegistro = materiaService.save(materiaDTO, id);

        return ResponseEntity.created(URI.create("/materia/" + id)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        if(materiaService.deleteById(id)){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
