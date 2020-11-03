package com.example.demo.service;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.dto.MentoriaDTO;
import com.example.demo.dto.NotaDTO;
import com.example.demo.dto.mapper.MateriaMapper;
import com.example.demo.dto.mapper.MentoriaMapper;
import com.example.demo.dto.mapper.NotaMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.WrongArgumentException;
import com.example.demo.model.Nota;
import com.example.demo.model.Programa;
import com.example.demo.repository.NotaRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotaService {

    @Autowired
    NotaRepository notaRepository;

    @Autowired
    private NotaMapper mapper;

    public NotaDTO findById(Long id) throws NotFoundException{
        //return notaRepository.findByActiveAndId(Boolean.TRUE, id).map(mapper::toNotaDTO);
        Optional<Nota> nota = notaRepository.findByActiveAndId(Boolean.TRUE, id);

        if(nota.isEmpty()){
            throw new NotFoundException("Nota não encontrado");
        }

        return mapper.toNotaDTO(nota.get());
    }

    public List<NotaDTO> findAll(){
        return notaRepository.findAllByActive(Boolean.TRUE)
                .parallelStream()
                .map(mapper::toNotaDTO)
                .collect(Collectors.toList());
    }

    public Long save(NotaDTO notaDTO) throws WrongArgumentException {
        Optional<Nota> notaOptional = Optional.of(mapper.toNota(notaDTO));

        if(notaOptional.isEmpty()){
            throw new WrongArgumentException("Nota com informações incorretas");
        }

        if(notaRepository.findIgual(notaDTO.getMateria_id(),
                notaDTO.getMentoria_id(),
                notaOptional.get().getData(),
                notaDTO.getId())
                .isPresent()){
            throw new WrongArgumentException("Nota para esse mês já lançada");
        }

        notaRepository.save(notaOptional.get());
        notaDTO.setId(notaOptional.get().getId());
        return notaDTO.getId();
    }

    public Long save(NotaDTO notaDTO, Long id) throws WrongArgumentException {
        notaDTO.setId(id);

        Optional<Nota> notaOptional = Optional.of(mapper.toNota(notaDTO));

        if(!notaRepository.existsByIdAndActive(id, Boolean.TRUE) || notaOptional.isEmpty()){
            throw new WrongArgumentException("Nota com informações incorretas");
        }

        if(notaRepository.findIgual(notaDTO.getMateria_id(),
                                    notaDTO.getMentoria_id(),
                                    notaOptional.get().getData(),
                                    id).isPresent()){
            throw new WrongArgumentException("Nota para esse mês já lançada");
        }

        notaOptional.get().setId(id);
        notaDTO.setId(id);
        notaRepository.save(notaOptional.get());

        return notaDTO.getId();
    }

    @Transactional
    public boolean delete(Long id){
        return inativar(notaRepository.findById(id));
    }

    @Transactional
    public boolean inativarNota(MateriaDTO materiaDTO){
        List<Nota> notas = notaRepository.findAllByActiveAndMateria_id(Boolean.TRUE, materiaDTO.getId());

        if(notas.size() == 0){
            return false;
        }

        notas.forEach(q -> inativar(Optional.of(q)));

        return true;
    }

    @Transactional
    public boolean inativarNota(MentoriaDTO mentoriaDTO){
        List<Nota> notas = notaRepository.findAllByActiveAndMentoria_id(Boolean.TRUE, mentoriaDTO.getId());

        if(notas.size() == 0){
            return false;
        }

        notas.forEach(q -> inativar(Optional.of(q)));

        return true;
    }

    private boolean inativar(Optional<Nota> notaOptional) throws NotFoundException{
        if(notaOptional.isEmpty()){
            throw new NotFoundException("Nota não encontrada");
        }

        notaOptional.get().setActive(Boolean.FALSE);

        return true;
    }
}
