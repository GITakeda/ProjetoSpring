package com.example.demo.dto.mapper.decorator;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.dto.MentoriaDTO;
import com.example.demo.dto.NotaDTO;
import com.example.demo.dto.mapper.MateriaMapper;
import com.example.demo.dto.mapper.MentoriaMapper;
import com.example.demo.dto.mapper.NotaMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Nota;
import com.example.demo.service.MateriaService;
import com.example.demo.service.MentoriaService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

@Data
public class NotaMapperDecorator implements NotaMapper {

    @Autowired
    MentoriaService mentoriaService;

    @Autowired
    MateriaService materiaService;

    @Autowired
    MentoriaMapper mentoriaMapper;

    @Autowired
    MateriaMapper materiaMapper;

    @Autowired
    @Qualifier("delegate")
    private NotaMapper delegated;

    @Override
    public NotaDTO toNotaDTO(Nota nota) {
        NotaDTO notaDTO = delegated.toNotaDTO(nota);

        MateriaDTO materiaDTO = materiaMapper.toMateriaDTO(nota.getMateria());
        MentoriaDTO mentoriaDTO = mentoriaMapper.toMentoriaDTO(nota.getMentoria());

        notaDTO.setMateriaDTO(materiaDTO);
        notaDTO.setMentoriaDTO(mentoriaDTO);

        return notaDTO;
    }

    @Override
    public Nota toNota(NotaDTO notaDTO) throws NotFoundException {
        Nota nota = delegated.toNota(notaDTO);

        MateriaDTO materiaDTOOptional = materiaService.findById(notaDTO.getMateriaDTO().getId());
        MentoriaDTO mentoriaDTOOptional = mentoriaService.findById(notaDTO.getMentoriaDTO().getId());

        nota.setMateria(materiaMapper.toMateria(materiaDTOOptional));
        nota.setMentoria(mentoriaMapper.toMentoria(mentoriaDTOOptional));

        return nota;
    }
}
