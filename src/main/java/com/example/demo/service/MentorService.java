package com.example.demo.service;

import com.example.demo.dto.MentorDTO;
import com.example.demo.dto.mapper.MentorMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Mentor;
import com.example.demo.repository.MentorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
        if(!mentorRepository.existsByIdAndActive(id, Boolean.TRUE)){
            throw new NotFoundException("Mentor não encontrado");
        }

        mentorDTO.setId(id);

        Mentor mentor = mentorRepository.save(mapper.toMentor(mentorDTO));

        mentorDTO.setId(mentor.getId());

        return mentorDTO.getId();
    }

    public boolean deleteById(Long id) throws NotFoundException{
        Optional<Mentor> mentor = mentorRepository.findByActiveAndId(Boolean.TRUE, id);

        if(mentor.isEmpty()){
           throw new NotFoundException("Mentor não encontrado");
        }

        mentor.get().setActive(Boolean.FALSE);

        try {
            mentoriaService.inativarMentoria(mapper.toMentorDTO(mentor.get()));
        } catch(NotFoundException e){
        } finally {
            mentorRepository.save(mentor.get());

            return true;
        }
    }

    public List<MentorDTO> findAll() {
        return mentorRepository.findAllByActive(Boolean.TRUE)
                            .parallelStream()
                            .map(mapper::toMentorDTO)
                            .collect(Collectors.toList());
    }

    public Page<MentorDTO> findAll(Pageable pageable){
        Page<Mentor> mentores = mentorRepository.findAllByActive(Boolean.TRUE, pageable);
        Page<MentorDTO> mentoresP = new PageImpl<MentorDTO>(mentores.getContent().parallelStream()
                .map(mapper::toMentorDTO)
                .collect(Collectors.toList()), mentores.getPageable(), mentores.getTotalElements());
        return mentoresP;
    }
}
