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
@NoArgsConstructor
@AllArgsConstructor
@Data
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
}
