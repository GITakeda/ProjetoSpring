package com.example.demo.controller;

import com.example.demo.dto.MentoriaDTO;
import com.example.demo.repository.MentoriaRepository;
import com.example.demo.service.MentoriaService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public ResponseEntity<MentoriaDTO> findById(@PathVariable Long id){
        //return mentoriaService.findById(id).map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
        return ResponseEntity.ok(mentoriaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<MentoriaDTO>> findAll(){
        return ResponseEntity.ok(mentoriaService.findAll());
    }

    @PostMapping("/post")
    public ResponseEntity<Boolean> save(@RequestBody MentoriaDTO mentoriaDTO){
        Long id = 0l;
        id = mentoriaService.save(mentoriaDTO);

        return ResponseEntity.created(URI.create("/mentoria/" + id)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> save(@RequestBody MentoriaDTO mentoriaDTO, @PathVariable Long id){
        Long idRetorno = 0l;
        idRetorno = mentoriaService.save(mentoriaDTO, id);

        return ResponseEntity.created(URI.create("/mentoria/" + idRetorno)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        mentoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
