package com.example.demo.service;

import com.example.demo.dto.ProgramaDTO;
import com.example.demo.dto.mapper.ProgramaMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.WrongArgumentException;
import com.example.demo.model.Programa;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.ProgramaRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgramaService {

    @Autowired
    ProgramaRepository programaRepository;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ProgramaMapper mapper;

    public ProgramaDTO findById(Long id) throws NotFoundException{
        Optional<Programa> programa = programaRepository.findByActiveAndId(Boolean.TRUE, id);

        if(programa.isEmpty()){
            throw new NotFoundException("Programa não encontrado");
        }

        return mapper.toProgramaDTO(programa.get());
    }

    public Long save(ProgramaDTO programaDTO){
        Programa programa = mapper.toPrograma(programaDTO);

        programaRepository.save(programa);

        programaDTO.setId(programa.getId());

        return programaDTO.getId();
    }

    public Long save(ProgramaDTO programaDTO, Long id) throws NotFoundException {
        if(!programaRepository.existsById(id)){
            throw new NotFoundException("Programa não encontrado");
        }

        programaDTO.setId(id);

        Programa programa = mapper.toPrograma(programaDTO);

        programaRepository.save(programa);

        programaDTO.setId(programa.getId());

        return programaDTO.getId();
    }

    public boolean deleteById(Long id) {
        Optional<Programa> programa = programaRepository.findByActiveAndId(Boolean.TRUE, id);

        if(programa.isEmpty()){
           return false;
        }

        if(alunoService.getAlunosByPrograma(id).size() > 0){
            throw new WrongArgumentException("Programa tem alunos ativos");
        }

        programa.get().setActive(Boolean.FALSE);

        programaRepository.save(programa.get());

        return true;
    }

    public List<ProgramaDTO> findAll() {
        return programaRepository.findAllByActive(Boolean.TRUE).parallelStream().map(mapper::toProgramaDTO).collect(Collectors.toList());
    }
}
