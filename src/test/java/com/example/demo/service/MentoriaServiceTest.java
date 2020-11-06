package com.example.demo.service;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.MentorDTO;
import com.example.demo.dto.MentoriaDTO;
import com.example.demo.dto.mapper.MentoriaMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.WrongArgumentException;
import com.example.demo.model.Aluno;
import com.example.demo.model.Mentor;
import com.example.demo.model.Mentoria;
import com.example.demo.repository.MentoriaRepository;
import com.example.demo.testFactory.AlunoTestFactory;
import com.example.demo.testFactory.MentorTestFactory;
import com.example.demo.testFactory.MentoriaTestFactory;
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
public class MentoriaServiceTest {

    @Mock
    MentorService mentorService;

    @Mock
    AlunoService alunoService;

    @Mock
    NotaService notaService;

    @Mock
    MentoriaRepository mentoriaRepository;

    @Mock
    MentoriaMapper mentoriaMapper;

    @InjectMocks
    MentoriaService mentoriaService;

    @Test
    public void testGetMentoriaComMentoriaExistente(){
        Long id = 1l;

        Mentoria mentoria = MentoriaTestFactory.createMentoria(id);
        MentoriaDTO mentoriaDTO = MentoriaTestFactory.createMentoriaDTO(id);

        Mockito.when(mentoriaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.of(mentoria));
        Mockito.when(mentoriaMapper.toMentoriaDTO(mentoria)).thenReturn(mentoriaDTO);

        compareMentoria(mentoriaDTO, mentoriaService.findById(id));
    }

    @Test
    public void testGetMentoriaComMentoriaInexistente(){
        Long id = 1l;

        Mentoria mentoria = MentoriaTestFactory.createMentoria(id);
        MentoriaDTO mentoriaDTO = MentoriaTestFactory.createMentoriaDTO(id);

        Mockito.when(mentoriaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> mentoriaService.findById(id));
    }

    @Test
    public void testGetAllMentorias(){
        Long id = 1l;

        Mentoria mentoria1 = MentoriaTestFactory.createMentoria(id);
        MentoriaDTO mentoriaDTO1 = MentoriaTestFactory.createMentoriaDTO(id);

        Mentoria mentoria2 = MentoriaTestFactory.createMentoria(id + 1);
        MentoriaDTO mentoriaDTO2 = MentoriaTestFactory.createMentoriaDTO(id + 1);

        List<Mentoria> mentoriaList = List.of(mentoria1, mentoria2);

        List<MentoriaDTO> mentoriaDTOList = List.of(mentoriaDTO1, mentoriaDTO2);

        Mockito.when(mentoriaRepository.findAllByActive(Boolean.TRUE)).thenReturn(mentoriaList);
        Mockito.when(mentoriaMapper.toMentoriaDTO(mentoria1)).thenReturn(mentoriaDTO1);
        Mockito.when(mentoriaMapper.toMentoriaDTO(mentoria2)).thenReturn(mentoriaDTO2);

        List<MentoriaDTO> retorno = mentoriaService.findAll();

        Assertions.assertEquals(mentoriaList.size(), retorno.size());

        int i = 0;

        while (i < retorno.size()){
            compareMentoria(mentoriaDTOList.get(i), retorno.get(i));
            i++;
        }
    }

    @Test
    public void testGravarMentoriaRepetida(){
        Long id = 1l;

        Mentoria mentoria = MentoriaTestFactory.createMentoria(id);
        MentoriaDTO mentoriaDTO = MentoriaTestFactory.createMentoriaDTO(id);

        Mockito.when(mentoriaRepository.findByActiveAndAluno_Id(Boolean.TRUE, id)).thenReturn(Optional.of(new Mentoria()));

        Assertions.assertThrows(WrongArgumentException.class, () -> mentoriaService.save(mentoriaDTO));
    }

    @Test
    public void testGravarMentoriaNova(){
        Long id = 1l;

        Mentoria mentoria = MentoriaTestFactory.createMentoria(id);
        MentoriaDTO mentoriaDTO = MentoriaTestFactory.createMentoriaDTO(id);

        Mockito.when(mentoriaRepository.findByActiveAndAluno_Id(Boolean.TRUE, id)).thenReturn(Optional.empty());
        Mockito.when(mentoriaMapper.toMentoria(mentoriaDTO)).thenReturn(mentoria);
        Mockito.when(mentoriaRepository.save(mentoria)).thenReturn(mentoria);

        Assertions.assertEquals(id, mentoriaService.save(mentoriaDTO));
    }

    @Test
    public void testAlterarMentoriaComMentoriaInexistente(){
        Long id = 1l;

        Mentoria mentoria = MentoriaTestFactory.createMentoria(id);
        MentoriaDTO mentoriaDTO = MentoriaTestFactory.createMentoriaDTO(id);

        Mockito.when(mentoriaMapper.toMentoria(mentoriaDTO)).thenReturn(mentoria);

        Assertions.assertThrows(NotFoundException.class, () -> mentoriaService.save(mentoriaDTO, id));
    }

    @Test
    public void testAlterarMentoriaComMentoriaRepetida(){
        Long id = 1l;

        Mentoria mentoria = MentoriaTestFactory.createMentoria(id);
        MentoriaDTO mentoriaDTO = MentoriaTestFactory.createMentoriaDTO(id);

        Mockito.when(mentoriaMapper.toMentoria(mentoriaDTO)).thenReturn(mentoria);
        Mockito.when(mentoriaRepository.existsByActiveAndId(Boolean.TRUE, id)).thenReturn(true);
        Mockito.when(mentoriaRepository.findIgual(id, id)).thenReturn(Optional.of(new Mentoria()));

        Assertions.assertThrows(WrongArgumentException.class, () -> mentoriaService.save(mentoriaDTO, id));
    }

    @Test
    public void testAlterarMentoriaComMentoriaExistente(){
        Long id = 1l;

        Mentoria mentoria = MentoriaTestFactory.createMentoria(id);
        MentoriaDTO mentoriaDTO = MentoriaTestFactory.createMentoriaDTO(id);

        Mockito.when(mentoriaMapper.toMentoria(mentoriaDTO)).thenReturn(mentoria);
        Mockito.when(mentoriaRepository.existsByActiveAndId(Boolean.TRUE, id)).thenReturn(true);
        Mockito.when(mentoriaRepository.findIgual(id, id)).thenReturn(Optional.empty());

        Assertions.assertEquals(id, mentoriaService.save(mentoriaDTO, id));
    }

    @Test
    public void testDeleteMentoriaComMentoriaExistente(){
        Long id = 1l;

        Mentoria mentoria = MentoriaTestFactory.createMentoria(id);
        MentoriaDTO mentoriaDTO = MentoriaTestFactory.createMentoriaDTO(id);

        Mockito.when(mentoriaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.of(mentoria));

        Assertions.assertTrue(mentoriaService.delete(id));
    }

    @Test
    public void testDeleteMentoriaComMentoriaInexistente(){
        Long id = 1l;

        Mentoria mentoria = MentoriaTestFactory.createMentoria(id);
        MentoriaDTO mentoriaDTO = MentoriaTestFactory.createMentoriaDTO(id);

        Mockito.when(mentoriaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> mentoriaService.delete(id));
    }

    private void compareMentoria(MentoriaDTO expected, MentoriaDTO actual){
        Assertions.assertAll(
                () -> Assertions.assertEquals(expected.getId(), actual.getId()),
                () -> Assertions.assertEquals(expected.getAluno_id(), actual.getAluno_id()),
                () -> Assertions.assertEquals(expected.getMentor_id(), actual.getMentor_id())
        );
    }

}
