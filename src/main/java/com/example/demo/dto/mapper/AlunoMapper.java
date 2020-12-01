package com.example.demo.dto.mapper;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.mapper.decorator.AlunoMapperDecorator;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Aluno;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
@DecoratedWith(AlunoMapperDecorator.class)
public interface AlunoMapper {

//    @Mapping(target = "active",constant = "true")
    Aluno toAluno(AlunoDTO alunoDTO) throws NotFoundException;

    AlunoDTO toAlunoDTO(Aluno aluno);

    @AfterMapping
    default AlunoDTO setProgramaDTO(@MappingTarget AlunoDTO alunoDTO, Aluno aluno) {
        ProgramaMapper programaMapper = Mappers.getMapper(ProgramaMapper.class);
        alunoDTO.setProgramaDTO(programaMapper.toProgramaDTO(aluno.getPrograma()));
        return alunoDTO;
    }
}
