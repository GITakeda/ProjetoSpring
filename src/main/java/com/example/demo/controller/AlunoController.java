package com.example.demo.controller;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> findById(@PathVariable Long id){
        //return alunoService.findById(id).map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
        return ResponseEntity.ok(alunoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AlunoDTO>> findAll(@PageableDefault() Pageable pageable ){
        return ResponseEntity.ok(alunoService.getAlunos(pageable));
    }

    @PostMapping("/post")
    public ResponseEntity save(@RequestBody AlunoDTO aluno){
        Long id = 0l;
        id = alunoService.save(aluno);

        return ResponseEntity.created(URI.create("/aluno/" + id)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity save(@RequestBody @Validated AlunoDTO alunoDTO, @PathVariable Long id){
        id = alunoService.save(alunoDTO, id);

        return ResponseEntity.created(URI.create("/aluno/" + id)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        alunoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
