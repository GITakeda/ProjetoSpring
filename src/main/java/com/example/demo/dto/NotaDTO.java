package com.example.demo.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class NotaDTO {
    private Long id;

    @NotNull
    private MentoriaDTO mentoriaDTO;

    @NotNull
    private MateriaDTO materiaDTO;

    private LocalDate data;

    @NotNull
    private Double pontuacao;

    public NotaDTO() {
    }

    public NotaDTO(Long id, @NotNull MentoriaDTO mentoriaDTO, @NotNull MateriaDTO materiaDTO, LocalDate data, @NotNull Double pontuacao) {
        this.id = id;
        this.mentoriaDTO = mentoriaDTO;
        this.materiaDTO = materiaDTO;
        this.data = data;
        this.pontuacao = pontuacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MentoriaDTO getMentoriaDTO() {
        return mentoriaDTO;
    }

    public void setMentoriaDTO(MentoriaDTO mentoriaDTO) {
        this.mentoriaDTO = mentoriaDTO;
    }

    public MateriaDTO getMateriaDTO() {
        return materiaDTO;
    }

    public void setMateriaDTO(MateriaDTO materiaDTO) {
        this.materiaDTO = materiaDTO;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Double pontuacao) {
        this.pontuacao = pontuacao;
    }
}
