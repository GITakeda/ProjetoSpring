package com.example.demo.controller;

import com.example.demo.dto.MentorDTO;
import com.example.demo.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public ResponseEntity<MentorDTO> findById(@PathVariable Long id) {
        //return mentorService.findById(id).map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
        return ResponseEntity.ok(mentorService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<MentorDTO>> findAll(){
        return ResponseEntity.ok(mentorService.findAll());
    }

    @PostMapping("/post")
    public ResponseEntity<Boolean> save(@RequestBody MentorDTO mentorDTO) {
        return ResponseEntity.created(URI.create("/mentor/" + mentorService.save(mentorDTO))).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> save(@RequestBody MentorDTO mentorDTO, @PathVariable Long id) {
        Long idRetorno = 0l;
        idRetorno = mentorService.save(mentorDTO, id);

        return ResponseEntity.created(URI.create("/mentor/" + idRetorno)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        mentorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}