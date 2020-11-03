package com.example.demo.service;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.ProgramaDTO;
import com.example.demo.dto.mapper.AlunoMapper;
import com.example.demo.dto.mapper.ProgramaMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Aluno;
import com.example.demo.repository.AlunoRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProgramaService programaService;

    @Autowired
    private MentoriaService mentoriaService;

    @Autowired
    private AlunoMapper mapper;

    public AlunoDTO findById(Long id){
        Optional<Aluno> registro = alunoRepository.findByActiveAndId(Boolean.TRUE, id);

        if(registro.isEmpty()){
            throw new NotFoundException("Aluno não encontrado");
        }

        return mapper.toAlunoDTO(registro.get());
    }

    public Long save(AlunoDTO alunoDTO) throws NotFoundException{
        if(alunoDTO.getProgramaDTO() == null){
            throw new NotFoundException("Informe o programa");
        }

        Aluno novoAluno = mapper.toAluno(alunoDTO);

        alunoRepository.save(novoAluno);
        alunoDTO.setId(novoAluno.getId());
        return alunoDTO.getId();
    }

    public Long save(AlunoDTO alunoDTO, Long id) throws NotFoundException{
        if(!alunoRepository.existsById(id)){
            //throw new Exception("Aluno não encontrado");
            throw new NotFoundException("Aluno não encontrado");
        }

        alunoDTO.setId(id);

        save(alunoDTO);

        return id;
    }

    public boolean deleteById(Long id){
        Optional<Aluno> aluno = alunoRepository.findByActiveAndId(Boolean.TRUE, id);

        if(aluno.isEmpty()){
            return false;
        }

        aluno.get().setActive(Boolean.FALSE);

        mentoriaService.inativarMentoria(mapper.toAlunoDTO(aluno.get()));

        alunoRepository.save(aluno.get());

        return true;
    }

    public List<AlunoDTO> getAlunos() {
        return alunoRepository.findAllByActive(Boolean.TRUE)
                              .parallelStream()
                              .map(mapper::toAlunoDTO)
                              .collect(Collectors.toList());
    }

    public List<AlunoDTO> getAlunosByPrograma(Long programa_id){
        return alunoRepository.findAllByActiveAndPrograma_id(Boolean.TRUE, programa_id)
                                .parallelStream()
                                .map(mapper::toAlunoDTO)
                                .collect(Collectors.toList());
    }
}
