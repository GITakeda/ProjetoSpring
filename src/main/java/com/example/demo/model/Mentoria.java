package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
