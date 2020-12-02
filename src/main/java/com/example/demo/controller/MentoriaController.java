package com.example.demo.controller;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.dto.MentoriaDTO;
import com.example.demo.repository.MentoriaRepository;
import com.example.demo.service.MentoriaService;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/mentoria")
public class MentoriaController {

    @Autowired
    MentoriaService mentoriaService;

    @ApiOperation(value = "Recurera a mentoria pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<MentoriaDTO> findById(@PathVariable Long id){
        //return mentoriaService.findById(id).map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
        return ResponseEntity.ok(mentoriaService.findById(id));
    }

    @ApiOperation(value = "Recupera todas as mentorias ativos")
    @GetMapping
    public ResponseEntity<Page<MentoriaDTO>> findAll(@PageableDefault() Pageable pageable ){
        return ResponseEntity.ok(mentoriaService.findAll(pageable));
    }

    @ApiOperation(value = "Grava uma nova mentoria")
    @PostMapping("/post")
    public ResponseEntity<Boolean> save(@RequestBody MentoriaDTO mentoriaDTO){
        Long id = 0l;
        id = mentoriaService.save(mentoriaDTO);

        return ResponseEntity.created(URI.create("/mentoria/" + id)).build();
    }

    @ApiOperation(value = "Atualiza uma mentoria existente")
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> save(@RequestBody MentoriaDTO mentoriaDTO, @PathVariable Long id){
        Long idRetorno = 0l;
        idRetorno = mentoriaService.save(mentoriaDTO, id);

        return ResponseEntity.created(URI.create("/mentoria/" + idRetorno)).build();
    }

    @ApiOperation(value = "Inativa uma mentoria pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        mentoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
