package com.example.demo.model;

import com.example.demo.dto.MentorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mentor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(columnDefinition = "BIT(1) default 1")
    private Boolean active;

//    public Mentor(MentorDTO mentorDTO){
//        this.setId(mentorDTO.getId());
//        this.setNome(mentorDTO.getNome());
//    }

//    public MentorDTO converterDTO(){
//        return new MentorDTO(id, nome);
//    }
}
