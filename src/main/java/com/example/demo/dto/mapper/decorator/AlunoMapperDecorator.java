package com.example.demo.dto.mapper.decorator;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.ProgramaDTO;
import com.example.demo.dto.mapper.AlunoMapper;
import com.example.demo.dto.mapper.ProgramaMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Aluno;
import com.example.demo.model.Programa;
import com.example.demo.service.ProgramaService;
import lombok.Data;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Data
public abstract class AlunoMapperDecorator implements AlunoMapper {

    @Autowired
    ProgramaService programaService;

    @Autowired
    ProgramaMapper programaMapper;

    @Autowired
    @Qualifier("delegate")
    private AlunoMapper delegate;

    @Override
    public Aluno toAluno(AlunoDTO alunoDTO) throws NotFoundException {
        Aluno aluno = delegate.toAluno(alunoDTO);

        ProgramaDTO programaOptional = programaService.findById(alunoDTO.getProgramaDTO().getId());

        aluno.setPrograma(programaMapper.toPrograma(programaOptional));

        return aluno;
    }

    @Override
    public AlunoDTO toAlunoDTO(Aluno aluno) {
        return delegate.toAlunoDTO(aluno);
    }
}
