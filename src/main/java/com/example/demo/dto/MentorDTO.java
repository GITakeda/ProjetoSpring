package com.example.demo.dto;

import javax.validation.constraints.NotNull;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class MentorDTO {
    private Long id;

    @NotNull
    private String nome;

    public MentorDTO(Long id, @NotNull String nome) {
        this.id = id;
        this.nome = nome;
    }

    public MentorDTO() {
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
}
