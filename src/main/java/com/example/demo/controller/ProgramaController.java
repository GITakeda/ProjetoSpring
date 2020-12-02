package com.example.demo.controller;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.dto.MentorDTO;
import com.example.demo.dto.ProgramaDTO;
import com.example.demo.model.Programa;
import com.example.demo.service.ProgramaService;
import io.swagger.annotations.ApiOperation;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/programa")
public class ProgramaController{

    @Autowired
    ProgramaService programaService;

    @ApiOperation(value = "Recurera o programa pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<ProgramaDTO> findById(@PathVariable Long id){
        //return programaService.findById(id).map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
        return ResponseEntity.ok(programaService.findById(id));
    }

    @ApiOperation(value = "Recupera todos os programas ativos")
    @GetMapping
    public ResponseEntity<Page<ProgramaDTO>> findAll(@PageableDefault() Pageable pageable ){
        return ResponseEntity.ok(programaService.findAll(pageable));
    }

    @ApiOperation(value = "Grava um novo programa")
    @PostMapping("/post")
    public ResponseEntity save(@RequestBody ProgramaDTO programa){
        return ResponseEntity.created(URI.create("/programa/" + programaService.save(programa))).build();
    }

    @ApiOperation(value = "Atualiza um programa existente")
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> save(@RequestBody ProgramaDTO programa, @PathVariable Long id){
        Long idRetorno = 0l;
        idRetorno = programaService.save(programa, id);

        return ResponseEntity.created(URI.create("/programa/" + idRetorno)).build();
    }

    @ApiOperation(value = "Inativa um programa pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity deletePrograma(@PathVariable Long id){
        programaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
