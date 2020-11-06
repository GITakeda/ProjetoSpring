package com.example.demo.service;

import com.example.demo.dto.NotaDTO;
import com.example.demo.dto.ProgramaDTO;
import com.example.demo.dto.mapper.NotaMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.WrongArgumentException;
import com.example.demo.model.Nota;
import com.example.demo.repository.NotaRepository;
import com.example.demo.testFactory.NotaTestFactory;
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
public class NotaServiceTest {

    @Mock
    NotaRepository notaRepository;

    @Mock
    NotaMapper notaMapper;

    @Mock
    MentoriaService mentoriaService;

    @Mock
    MateriaService materiaService;

    @InjectMocks
    NotaService notaService;

    @Test
    public void testGetNotaComNotaExistente(){
        Long id = 1l;

        Nota nota = NotaTestFactory.createNota(id);
        NotaDTO notaDTO = NotaTestFactory.createNotaDTO(id);

        Mockito.when(notaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.of(nota));
        Mockito.when(notaMapper.toNotaDTO(nota)).thenReturn(notaDTO);

        compareNota(notaDTO, notaService.findById(id));
    }

    @Test
    public void testGetNotaComNotaInexistente(){
        Long id = 1l;

        Nota nota = NotaTestFactory.createNota(id);
        NotaDTO notaDTO = NotaTestFactory.createNotaDTO(id);

        Mockito.when(notaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> notaService.findById(id));
    }

    @Test
    public void testGetAll(){
        Long id = 1l;

        Nota nota1 = NotaTestFactory.createNota(id);
        NotaDTO notaDTO1 = NotaTestFactory.createNotaDTO(id);

        Nota nota2 = NotaTestFactory.createNota(id + 1);
        NotaDTO notaDTO2 = NotaTestFactory.createNotaDTO(id + 1);

        List<Nota> notaList = List.of(nota1, nota2);
        List<NotaDTO> notaDTOList = List.of(notaDTO1, notaDTO2);

        Mockito.when(notaRepository.findAllByActive(Boolean.TRUE)).thenReturn(notaList);
        Mockito.when(notaMapper.toNotaDTO(nota1)).thenReturn(notaDTO1);
        Mockito.when(notaMapper.toNotaDTO(nota2)).thenReturn(notaDTO2);

        List<NotaDTO> retorno = notaService.findAll();

        Assertions.assertEquals(notaDTOList.size(), retorno.size());

        int i = 0;

        while(i < retorno.size()){
            compareNota(notaDTOList.get(i), retorno.get(i));
            i++;
        }
    }

    @Test
    public void testGravarNotaRepetida(){
        Long id = 1l;

        Nota nota = NotaTestFactory.createNota(id);
        NotaDTO notaDTO = NotaTestFactory.createNotaDTO(id);

        Mockito.when(notaMapper.toNota(notaDTO)).thenReturn(nota);
        Mockito.when(notaRepository.findIgual(notaDTO.getMateria_id(),
                notaDTO.getMentoria_id(),
                notaDTO.getData(),
                notaDTO.getId())).thenReturn(Optional.of(new Nota()));

        Assertions.assertThrows(WrongArgumentException.class, () -> notaService.save(notaDTO));
    }

    @Test
    public void testGravarNotaNova(){
        Long id = 1l;

        Nota nota = NotaTestFactory.createNota(id);
        NotaDTO notaDTO = NotaTestFactory.createNotaDTO(id);

        Mockito.when(notaMapper.toNota(notaDTO)).thenReturn(nota);
        Mockito.when(notaRepository.findIgual(notaDTO.getMateria_id(),
                notaDTO.getMentoria_id(),
                notaDTO.getData(),
                notaDTO.getId())).thenReturn(Optional.empty());

        Assertions.assertEquals(id, notaService.save(notaDTO));
    }

    @Test
    public void testAlterarNotaComNotaInexistente(){
        Long id = 1l;

        Nota nota = NotaTestFactory.createNota(id);
        NotaDTO notaDTO = NotaTestFactory.createNotaDTO(id);

        Mockito.when(notaMapper.toNota(notaDTO)).thenReturn(nota);
        Mockito.when(notaRepository.existsByIdAndActive(id, Boolean.TRUE)).thenReturn(false);

        Assertions.assertThrows(NotFoundException.class, () -> notaService.save(notaDTO, id));
    }

    @Test
    public void testAlterarNotaComNotaRepetida(){
        Long id = 1l;

        Nota nota = NotaTestFactory.createNota(id);
        NotaDTO notaDTO = NotaTestFactory.createNotaDTO(id);

        Mockito.when(notaMapper.toNota(notaDTO)).thenReturn(nota);
        Mockito.when(notaRepository.existsByIdAndActive(id, Boolean.TRUE)).thenReturn(true);
        Mockito.when(notaRepository.findIgual(notaDTO.getMateria_id(),
                notaDTO.getMentoria_id(), notaDTO.getData(), notaDTO.getId())).thenReturn(Optional.of(new Nota()));

        Assertions.assertThrows(WrongArgumentException.class, () -> notaService.save(notaDTO, id));
    }

    @Test
    public void testAlterarNotaComNotaExistente(){
        Long id = 1l;

        Nota nota = NotaTestFactory.createNota(id);
        NotaDTO notaDTO = NotaTestFactory.createNotaDTO(id);

        Mockito.when(notaMapper.toNota(notaDTO)).thenReturn(nota);
        Mockito.when(notaRepository.existsByIdAndActive(id, Boolean.TRUE)).thenReturn(true);
        Mockito.when(notaRepository.findIgual(notaDTO.getMateria_id(),
                notaDTO.getMentoria_id(), notaDTO.getData(), notaDTO.getId())).thenReturn(Optional.empty());

        Assertions.assertEquals(id, notaService.save(notaDTO, id));
    }

    @Test
    public void testDeletarNotaInexistente(){
        Long id = 1l;

        Nota nota = NotaTestFactory.createNota(id);
        NotaDTO notaDTO = NotaTestFactory.createNotaDTO(id);

        Mockito.when(notaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> notaService.delete(id));
    }

    @Test
    public void testDeletarNotaExistente(){
        Long id = 1l;

        Nota nota = NotaTestFactory.createNota(id);
        NotaDTO notaDTO = NotaTestFactory.createNotaDTO(id);

        Mockito.when(notaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.of(nota));

        Assertions.assertTrue(notaService.delete(id));
    }

    private void compareNota(NotaDTO expected, NotaDTO actual){
        Assertions.assertAll(
                () -> Assertions.assertEquals(expected.getId(), actual.getId()),
                () -> Assertions.assertEquals(expected.getMateria_id(), actual.getMentoria_id()),
                () -> Assertions.assertEquals(expected.getMentoria_id(), actual.getMentoria_id()),
                () -> Assertions.assertEquals(expected.getData(), actual.getData()),
                () -> Assertions.assertEquals(expected.getPontuacao(), actual.getPontuacao())
        );
    }
}
