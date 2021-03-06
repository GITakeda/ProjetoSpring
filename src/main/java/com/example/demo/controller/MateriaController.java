package com.example.demo.controller;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.MateriaDTO;
import com.example.demo.service.MateriaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    MateriaService materiaService;

    @ApiOperation(value = "Recurera a materia pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTO> findById(@PathVariable Long id){
//        return materiaService.findById(id).map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
        return ResponseEntity.ok(materiaService.findById(id));
    }

    @ApiOperation(value = "Recupera todas as materias ativos")
    @GetMapping
    public ResponseEntity<Page<MateriaDTO>> findAll(@PageableDefault() Pageable pageable ){
        return ResponseEntity.ok(materiaService.findAll(pageable));
    }

    @ApiOperation(value = "Grava uma nova materia")
    @PostMapping("/post")
    public ResponseEntity<Boolean> save(@RequestBody MateriaDTO materiaDTO){
        return ResponseEntity.created(URI.create("/materia/" + materiaService.save(materiaDTO))).build();
    }

    @ApiOperation(value = "Atualiza uma materia existente")
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> save(@RequestBody MateriaDTO materiaDTO, @PathVariable Long id){
        Long idRegistro = 0l;

        idRegistro = materiaService.save(materiaDTO, id);

        return ResponseEntity.created(URI.create("/materia/" + id)).build();
    }

    @ApiOperation(value = "Inativa uma materia pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        materiaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
