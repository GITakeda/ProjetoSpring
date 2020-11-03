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
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Programa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String ano;

    @Column(columnDefinition = "BIT(1) default 1")
    private Boolean active;

//    public Programa(ProgramaDTO programaDTO){
//        this.nome = programaDTO.getNome();
//        this.ano = programaDTO.getAno();
//        this.id = programaDTO.getId();
//    }

//    public ProgramaDTO converterDTO(){
//        return new ProgramaDTO(id, nome, ano);
//    }
}
