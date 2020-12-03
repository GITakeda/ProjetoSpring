package com.example.demo.model;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Mentoria mentoria;

    @NotNull
    @ManyToOne
    private Materia materia;

    @NotNull
    private LocalDate data;

    private Double pontuacao;

    @Column(columnDefinition = "BIT(1) default 1")
    private Boolean active;

    public Nota() {
        this.active = true;
    }

    public Nota(Long id, Mentoria mentoria, Materia materia, LocalDate data, Double pontuacao, Boolean active) {
        this.id = id;
        this.mentoria = mentoria;
        this.materia = materia;
        this.data = data;
        this.pontuacao = pontuacao;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mentoria getMentoria() {
        return mentoria;
    }

    public void setMentoria(Mentoria mentoria) {
        this.mentoria = mentoria;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
