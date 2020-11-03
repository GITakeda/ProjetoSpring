package com.example.demo.service;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.MentorDTO;
import com.example.demo.dto.MentoriaDTO;
import com.example.demo.dto.mapper.AlunoMapper;
import com.example.demo.dto.mapper.MentorMapper;
import com.example.demo.dto.mapper.MentoriaMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.WrongArgumentException;
import com.example.demo.model.Mentoria;
import com.example.demo.repository.MentoriaRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MentoriaService {

    @Autowired
    MentoriaRepository mentoriaRepository;

    @Autowired
    NotaService notaService;

    @Autowired
    private MentoriaMapper mapper;

    public MentoriaDTO findById(Long id){
        Optional<Mentoria> mentoria = mentoriaRepository.findByActiveAndId(Boolean.TRUE, id);

        if(mentoria.isEmpty()){
            throw new NotFoundException("Mentoria não encontrada");
        }

        return mapper.toMentoriaDTO(mentoria.get());
    }

    public List<MentoriaDTO> findAll(){
        return mentoriaRepository.findAllByActive(Boolean.TRUE).parallelStream().map(mapper::toMentoriaDTO).collect(Collectors.toList());
    }

    public Long save(MentoriaDTO mentoriaDTO) throws WrongArgumentException{
        Mentoria mentoria = mapper.toMentoria(mentoriaDTO);

        if(mentoriaRepository.findByActiveAndAluno_Id(Boolean.TRUE, mentoriaDTO.getAluno_id()).isPresent()){
            throw new WrongArgumentException("Mentoria não criada");
        }

        mentoria = mentoriaRepository.save(mentoria);

        mentoriaDTO.setId(mentoria.getId());

        return mentoriaDTO.getId();
    }

    public Long save(MentoriaDTO mentoriaDTO, Long id) throws NotFoundException {
        mentoriaDTO.setId(id);

        Mentoria mentoria = mapper.toMentoria(mentoriaDTO);

        if(!mentoriaRepository.existsByActiveAndId(Boolean.TRUE, id)){
            throw new NotFoundException("Mentoria não encontrada");
        }

        if(mentoriaRepository.findIgual(mentoriaDTO.getAluno_id(), id).isPresent()){
            throw new WrongArgumentException("Mentoria já criada para o aluno inserido");
        }

        mentoriaRepository.save(mentoria);

        return id;
    }

    public boolean delete(Long id){
        return inativar(mentoriaRepository.findByActiveAndId(true, id));
    }

    protected void inativarMentoria(AlunoDTO alunoDTO){
        inativar(mentoriaRepository.findByActiveAndAluno_Id(Boolean.TRUE, alunoDTO.getId()));
    }

    protected void inativarMentoria(MentorDTO mentorDTO){
        inativar(mentoriaRepository.findByActiveAndMentor_Id(Boolean.TRUE, mentorDTO.getId()));
    }

    private boolean inativar(Optional<Mentoria> mentoria) throws NotFoundException{
        if(mentoria.isEmpty()){
            throw new NotFoundException("Mentoria não encontrada");
        }

        mentoria.get().setActive(Boolean.FALSE);

        notaService.inativarNota(mapper.toMentoriaDTO(mentoria.get()));

        mentoriaRepository.save(mentoria.get());

        return true;
    }
}
