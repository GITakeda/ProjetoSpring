package com.example.demo.model;

import com.example.demo.dto.MentorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Mentor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(columnDefinition = "BIT(1) default 1")
    private Boolean active;

    public Mentor(Long id, String nome, Boolean active) {
        this.id = id;
        this.nome = nome;
        this.active = active;
    }

    public Mentor() {
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    //    public Mentor(MentorDTO mentorDTO){
//        this.setId(mentorDTO.getId());
//        this.setNome(mentorDTO.getNome());
//    }

//    public MentorDTO converterDTO(){
//        return new MentorDTO(id, nome);
//    }
}
