package com.example.demo.service;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.dto.MentorDTO;
import com.example.demo.dto.mapper.MentorMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Materia;
import com.example.demo.model.Mentor;
import com.example.demo.repository.MentorRepository;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MentorServiceTest {

    @Mock
    private MentorMapper mentorMapper;

    @Mock
    private MentorRepository mentorRepository;

    @Mock
    private MentoriaService mentoriaService;

    @InjectMocks
    private MentorService mentorService;

    @Test
    public void testGetMentorComMentorExistente(){
        Long id = 1l;

        Mentor mentor = new Mentor(id, "nome", Boolean.TRUE);
        MentorDTO mentorDTO = new MentorDTO(id, "nome");

        Mockito.when(mentorRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.of(mentor));
        Mockito.when(mentorMapper.toMentorDTO(mentor)).thenReturn(mentorDTO);

        compareMentor(mentorDTO, mentorService.findById(id));
    }

    @Test
    public void testGetMentorComMentorInexistente(){
        Long id = 1l;

        Mentor mentor = new Mentor(id, "nome", Boolean.TRUE);
        MentorDTO mentorDTO = new MentorDTO(id, "nome");

        Mockito.when(mentorRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> mentorService.findById(id));
    }

    @Test
    public void testGetAllMentores(){
        Long id = 1l;

        Mentor mentor1 = new Mentor(id, "nome", Boolean.TRUE);
        MentorDTO mentorDTO1 = new MentorDTO(id, "nome");
        Mentor mentor2 = new Mentor(id + 1, "nome", Boolean.TRUE);
        MentorDTO mentorDTO2 = new MentorDTO(id, "nome");

        List<Mentor> mentores = List.of(mentor1, mentor2);
        List<MentorDTO> mentorDTOS = List.of(mentorDTO1, mentorDTO2);

        Mockito.when(mentorRepository.findAllByActive(Boolean.TRUE)).thenReturn(mentores);
        Mockito.when(mentorMapper.toMentorDTO(mentor1)).thenReturn(mentorDTO1);
        Mockito.when(mentorMapper.toMentorDTO(mentor2)).thenReturn(mentorDTO2);

        List<MentorDTO> retorno = mentorService.findAll();

        Long i = 0l;

        Assertions.assertEquals(mentorDTOS.size(), retorno.size());

        while(i < retorno.size()){
            compareMentor(mentorDTOS.get(i.intValue()), retorno.get(i.intValue()));
            i++;
        }
    }

    @Test
    public void testGravarMentor(){
        Long id = 1l;

        Mentor mentor = new Mentor(id, "nome", Boolean.TRUE);
        MentorDTO mentorDTO = new MentorDTO(id, "nome");

        Mockito.when(mentorMapper.toMentor(mentorDTO)).thenReturn(mentor);
        Mockito.when(mentorRepository.save(mentor)).thenReturn(mentor);

        Assertions.assertEquals(id, mentorService.save(mentorDTO));
    }

    @Test
    public void testAlterarMentorComMentorInexistente(){
        Long id = 1L;

        MentorDTO mentorDTO = new MentorDTO(id, "nome");
        Mentor mentor = new Mentor(id, "nome", Boolean.TRUE);

        MentorDTO mentorDTOAlterado = new MentorDTO(id, "nome");
        Mentor mentorAlterado = new Mentor(id, "nome", Boolean.TRUE);

        Assertions.assertThrows(NotFoundException.class, () -> mentorService.save(mentorDTOAlterado, id));
    }

    @Test
    public void testAlterarMentorComMentorExistente(){
        Long id = 1L;

        MentorDTO mentorDTO = new MentorDTO(id, "nome");
        Mentor mentor = new Mentor(id, "nome", Boolean.TRUE);

        MentorDTO mentorDTOAlterado = new MentorDTO(id, "nome");
        Mentor mentorAlterado = new Mentor(id, "nome", Boolean.TRUE);

        Mockito.when(mentorRepository.existsByIdAndActive(id, Boolean.TRUE)).thenReturn(true);
        Mockito.when(mentorRepository.save(mentorMapper.toMentor(mentorDTO))).thenReturn(mentor);

        Assertions.assertEquals(id, mentorService.save(mentorDTOAlterado, id));
    }

    @Test
    public void testDeleteMentorComMentorInexistente(){
        Long id = 1L;

        MentorDTO mentorDTO = new MentorDTO(id, "nome");
        Mentor mentor = new Mentor(id, "nome", Boolean.TRUE);

        Mockito.when(mentorRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> mentorService.deleteById(id));
    }

    @Test
    public void testDeleteMentorComMentorExistente(){
        Long id = 1L;

        MentorDTO mentorDTO = new MentorDTO(id, "nome");
        Mentor mentor = new Mentor(id, "nome", Boolean.TRUE);

        Mockito.when(mentorRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.of(mentor));
        Mockito.when(mentorMapper.toMentorDTO(mentor)).thenReturn(mentorDTO);

        Assertions.assertEquals(Boolean.TRUE, mentorService.deleteById(id));
    }

    private void compareMentor(MentorDTO expected, MentorDTO actual){
        Assertions.assertAll(
                () -> Assertions.assertEquals(expected.getId(), actual.getId()),
                () -> Assertions.assertEquals(expected.getNome(), actual.getNome())
        );
    }
}
