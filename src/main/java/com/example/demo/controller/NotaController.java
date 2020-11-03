package com.example.demo.controller;

import com.example.demo.dto.NotaDTO;
import com.example.demo.model.Nota;
import com.example.demo.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public ResponseEntity<NotaDTO> findById(@PathVariable Long id){
        //return notaService.findById(id).map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
        return ResponseEntity.ok(notaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<NotaDTO>> findAll(){
        return ResponseEntity.ok(notaService.findAll());
    }

    @PostMapping("/post")
    public ResponseEntity save(@RequestBody NotaDTO notaDTO){
        Long id = 0l;
        try {
            id = notaService.save(notaDTO);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.created(URI.create("/nota/" + id)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity save(@RequestBody NotaDTO notaDTO, @PathVariable Long id){
        Long idRegistro = 0l;
        try{
            idRegistro = notaService.save(notaDTO, id);

        } catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.created(URI.create("/nota/" + idRegistro)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        notaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
