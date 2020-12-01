package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Mentoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "aluno_id")
    private Aluno aluno;

    @OneToOne
    @JoinColumn(name= "mentor_id")
    private Mentor mentor;

    @Column(columnDefinition = "BIT(1) default 1")
    private Boolean active;

    public Mentoria() {
    }

    public Mentoria(Long id, Aluno aluno, Mentor mentor, Boolean active) {
        this.id = id;
        this.aluno = aluno;
        this.mentor = mentor;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
