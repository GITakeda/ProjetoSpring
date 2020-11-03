package com.example.demo.dto;

import com.example.demo.model.Mentoria;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapping;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MentoriaDTO {

    private Long id;

    @NotNull
    private Long mentor_id;

    @NotNull
    private Long aluno_id;

    public MentoriaDTO(Mentoria mentoria){
        this.id = mentoria.getId();
        this.mentor_id = mentoria.getMentor().getId();
        this.aluno_id = mentoria.getAluno().getId();
    }
}
