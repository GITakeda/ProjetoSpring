package com.example.demo.controller;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.dto.NotaDTO;
import com.example.demo.model.Nota;
import com.example.demo.service.NotaService;
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

@RestController
@RequestMapping("/nota")
public class NotaController {

    @Autowired
    NotaService notaService;

    @ApiOperation(value = "Recurera a nota pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<NotaDTO> findById(@PathVariable Long id){
        //return notaService.findById(id).map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
        return ResponseEntity.ok(notaService.findById(id));
    }

    @ApiOperation(value = "Recupera todas as notas ativos")
    @GetMapping
    public ResponseEntity<Page<NotaDTO>> findAll(@PageableDefault() Pageable pageable ){
        return ResponseEntity.ok(notaService.findAll(pageable));
    }

    @ApiOperation(value = "Grava uma nova nota")
    @PostMapping("/post")
    public ResponseEntity save(@RequestBody NotaDTO notaDTO){
        Long id = 0l;

        id = notaService.save(notaDTO);

        return ResponseEntity.created(URI.create("/nota/" + id)).build();
    }

    @ApiOperation(value = "Atualiza uma nota existente")
    @PutMapping("/{id}")
    public ResponseEntity save(@RequestBody NotaDTO notaDTO, @PathVariable Long id){
        Long idRegistro = 0l;

        idRegistro = notaService.save(notaDTO, id);

        return ResponseEntity.created(URI.create("/nota/" + idRegistro)).build();
    }

    @ApiOperation(value = "Inativa uma nota pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        notaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
