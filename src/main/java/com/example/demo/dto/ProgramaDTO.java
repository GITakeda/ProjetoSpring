package com.example.demo.dto;


import com.example.demo.model.Programa;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ProgramaDTO {

    private Long id;

    private String nome;

    private String ano;

    public ProgramaDTO() {
    }

    public ProgramaDTO(Long id, String nome, String ano) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
