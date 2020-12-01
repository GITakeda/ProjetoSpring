package com.example.demo.dto;

import com.example.demo.model.Aluno;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Validated
public class AlunoDTO {

    private Long id;

    @NotNull(message = "Informe um nome correto")
    private String nome;

    private String classe;

    @NotNull(message = "Informe o programa")
    private ProgramaDTO programaDTO;

    public AlunoDTO(Long id, @NotNull(message = "Informe um nome correto") String nome, String classe, @NotNull(message = "Informe o programa") ProgramaDTO programaDTO) {
        this.id = id;
        this.nome = nome;
        this.classe = classe;
        this.programaDTO = programaDTO;
    }

    public AlunoDTO() {
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

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public ProgramaDTO getProgramaDTO() {
        return programaDTO;
    }

    public void setProgramaDTO(ProgramaDTO programaDTO) {
        this.programaDTO = programaDTO;
    }
}
