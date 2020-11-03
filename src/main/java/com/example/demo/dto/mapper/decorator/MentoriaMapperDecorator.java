package com.example.demo.dto.mapper.decorator;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.MentorDTO;
import com.example.demo.dto.MentoriaDTO;
import com.example.demo.dto.mapper.AlunoMapper;
import com.example.demo.dto.mapper.MentorMapper;
import com.example.demo.dto.mapper.MentoriaMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Mentor;
import com.example.demo.model.Mentoria;
import com.example.demo.service.AlunoService;
import com.example.demo.service.MentorService;
import lombok.Data;
import org.mapstruct.DecoratedWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

@Data
public class MentoriaMapperDecorator implements MentoriaMapper {

    @Autowired
    MentorService mentorService;

    @Autowired
    AlunoService alunoService;

    @Autowired
    AlunoMapper alunoMapper;

    @Autowired
    MentorMapper mentorMapper;

    @Autowired
    @Qualifier("delegate")
    private MentoriaMapper delegate;

    @Override
    public MentoriaDTO toMentoriaDTO(Mentoria mentoria) {
        return delegate.toMentoriaDTO(mentoria);
    }

    @Override
    public Mentoria toMentoria(MentoriaDTO mentoriaDTO) throws NotFoundException {
        Mentoria mentoria = delegate.toMentoria(mentoriaDTO);

        MentorDTO mentorDTOOptional = mentorService.findById(mentoriaDTO.getMentor_id());
        AlunoDTO alunoDTOOptional = alunoService.findById(mentoriaDTO.getAluno_id());

        mentoria.setMentor(mentorMapper.toMentor(mentorDTOOptional));

        mentoria.setAluno(alunoMapper.toAluno(alunoDTOOptional));

        return mentoria;
    }
}
