package com.example.demo.controller;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.dto.MentorDTO;
import com.example.demo.service.MentorService;
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
@RequestMapping("/mentor")
public class MentorController {

    @Autowired
    MentorService mentorService;

    @ApiOperation(value = "Recurera o mentor pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<MentorDTO> findById(@PathVariable Long id) {
        //return mentorService.findById(id).map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
        return ResponseEntity.ok(mentorService.findById(id));
    }

    @ApiOperation(value = "Recupera todos os mentors ativos")
    @GetMapping
    public ResponseEntity<Page<MentorDTO>> findAll(@PageableDefault() Pageable pageable ){
        return ResponseEntity.ok(mentorService.findAll(pageable));
    }

    @ApiOperation(value = "Grava um novo mentor")
    @PostMapping("/post")
    public ResponseEntity<Boolean> save(@RequestBody MentorDTO mentorDTO) {
        return ResponseEntity.created(URI.create("/mentor/" + mentorService.save(mentorDTO))).build();
    }

    @ApiOperation(value = "Atualiza um mentor existente")
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> save(@RequestBody MentorDTO mentorDTO, @PathVariable Long id) {
        Long idRetorno = 0l;
        idRetorno = mentorService.save(mentorDTO, id);

        return ResponseEntity.created(URI.create("/mentor/" + idRetorno)).build();
    }

    @ApiOperation(value = "Inativa um mentor pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        mentorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}