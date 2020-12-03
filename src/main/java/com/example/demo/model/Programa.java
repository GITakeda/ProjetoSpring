package com.example.demo.model;

import com.example.demo.dto.ProgramaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
public class Programa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String ano;

    @Column(columnDefinition = "BIT(1) default 1")
    private Boolean active;

    public Programa() {
        this.active = true;
    }

    public Programa(Long id, String nome, String ano, Boolean active) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.active = active;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
