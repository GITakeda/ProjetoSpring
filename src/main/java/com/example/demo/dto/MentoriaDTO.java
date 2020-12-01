package com.example.demo.dto;

import com.example.demo.model.Mentoria;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapping;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class MentoriaDTO {

    private Long id;

    @NotNull
    private MentorDTO mentorDTO;

    @NotNull
    private AlunoDTO alunoDTO;

    public MentoriaDTO() {
    }

    public MentoriaDTO(Long id, @NotNull MentorDTO mentorDTO, @NotNull AlunoDTO alunoDTO) {
        this.id = id;
        this.mentorDTO = mentorDTO;
        this.alunoDTO = alunoDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MentorDTO getMentorDTO() {
        return mentorDTO;
    }

    public void setMentorDTO(MentorDTO mentorDTO) {
        this.mentorDTO = mentorDTO;
    }

    public AlunoDTO getAlunoDTO() {
        return alunoDTO;
    }

    public void setAlunoDTO(AlunoDTO alunoDTO) {
        this.alunoDTO = alunoDTO;
    }
}
