package com.example.demo.service;

import com.example.demo.dto.MentorDTO;
import com.example.demo.dto.mapper.MentorMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Mentor;
import com.example.demo.repository.MentorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MentorService {

    @Autowired
    MentorRepository mentorRepository;

    @Autowired
    MentoriaService mentoriaService;

    @Autowired
    private MentorMapper mapper;

    public MentorDTO findById(Long id) throws NotFoundException{
        Optional<Mentor> mentor = mentorRepository.findByActiveAndId(Boolean.TRUE, id);

        if(mentor.isEmpty()) {
            throw new NotFoundException("Mentor não encontrado");
        }

        return mapper.toMentorDTO(mentor.get());
    }

    public Long save(MentorDTO mentorDTO){
        Mentor mentor = mentorRepository.save(mapper.toMentor(mentorDTO));

        mentorDTO.setId(mentor.getId());

        return mentorDTO.getId();
    }

    public Long save(MentorDTO mentorDTO, Long id) throws NotFoundException{
        if(!mentorRepository.existsById(id)){
            throw new NotFoundException("Mentor não encontrado");
        }

        mentorDTO.setId(id);

        Mentor mentor = mentorRepository.save(mapper.toMentor(mentorDTO));

        mentorDTO.setId(mentor.getId());

        return mentorDTO.getId();
    }

    public boolean deleteById(Long id) {
        Optional<Mentor> mentor = mentorRepository.findByActiveAndId(Boolean.TRUE, id);

        if(mentor.isEmpty()){
           return false;
        }

        mentor.get().setActive(Boolean.FALSE);

        mentoriaService.inativarMentoria(mapper.toMentorDTO(mentor.get()));

        mentorRepository.save(mentor.get());

        return true;
    }

    public List<MentorDTO> findAll() {
        return mentorRepository.findAllByActive(Boolean.TRUE)
                            .parallelStream()
                            .map(mapper::toMentorDTO)
                            .collect(Collectors.toList());
    }
}
