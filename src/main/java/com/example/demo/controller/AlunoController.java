package com.example.demo.controller;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.service.AlunoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "Aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @ApiOperation(value = "Recurera o aluno pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> findById(@PathVariable Long id){
        //return alunoService.findById(id).map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
        return ResponseEntity.ok(alunoService.findById(id));
    }

    @ApiOperation(value = "Recupera todos os alunos ativos")
    @GetMapping
    public ResponseEntity<Page<AlunoDTO>> findAll(@PageableDefault() Pageable pageable ){
        return ResponseEntity.ok(alunoService.getAlunos(pageable));
    }

    @ApiOperation(value = "Grava um novo aluno")
    @PostMapping("/post")
    public ResponseEntity save(@RequestBody AlunoDTO aluno){
        Long id = 0l;
        id = alunoService.save(aluno);

        return ResponseEntity.created(URI.create("/aluno/" + id)).build();
    }

    @ApiOperation(value = "Atualiza um aluno existente")
    @PutMapping("/{id}")
    public ResponseEntity save(@RequestBody @Validated AlunoDTO alunoDTO, @PathVariable Long id){
        id = alunoService.save(alunoDTO, id);

        return ResponseEntity.created(URI.create("/aluno/" + id)).build();
    }

    @ApiOperation(value = "Inativa um aluno pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        alunoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
