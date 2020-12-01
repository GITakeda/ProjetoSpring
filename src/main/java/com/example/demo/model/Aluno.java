package com.example.demo.model;

import com.example.demo.dto.AlunoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
public class Aluno {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="programa_id", nullable = false)
    private Programa programa;

    private String nome;
    private String classe;

    @Column(columnDefinition = "BIT(1) default 1")
    private Boolean active;

    public Aluno() {
    }

    public Aluno(Long id, Programa programa, String nome, String classe, Boolean active) {
        this.id = id;
        this.programa = programa;
        this.nome = nome;
        this.classe = classe;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
