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
    private MentorDTO mentorDTO;

    @NotNull
    private AlunoDTO alunoDTO;
}
