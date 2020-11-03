package com.example.demo.service;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.dto.mapper.MateriaMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Materia;
import com.example.demo.repository.MateriaRepository;
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
public class MateriaServiceTest {

    @Mock
    private MateriaRepository materiaRepository;

    @Mock
    private MateriaMapper materiaMapper;

    @Mock
    private NotaService notaService;

    @InjectMocks
    private MateriaService materiaService;

    @Test
    public void testGetMateriaComMateriaExistente(){
        Long id = 1L;

        MateriaDTO materiaDTO = new MateriaDTO(id, "nome", "descricao");
        Materia materia = new Materia(id, "nome", "descricao", Boolean.TRUE);

        Mockito.when(materiaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.of(materia));
        Mockito.when(materiaMapper.toMateriaDTO(materia)).thenReturn(materiaDTO);

        compareMateria(materiaDTO, materiaService.findById(id));
    }

    @Test
    public void testGetMateriaComMateriaInexistente(){
        Long id = 1L;

        MateriaDTO materiaDTO = new MateriaDTO(id, "nome", "descricao");
        Materia materia = new Materia(id, "nome", "descricao", Boolean.TRUE);

        Mockito.when(materiaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> materiaService.findById(id));
    }

    @Test
    public void testGetAllMateria(){
        Long id = 1L;

        MateriaDTO materiaDTO1 = new MateriaDTO(id, "nome", "descricao");
        MateriaDTO materiaDTO2 = new MateriaDTO(id + 1, "nome", "descricao");

        Materia materia1 = new Materia(id, "nome", "descricao", Boolean.TRUE);
        Materia materia2 = new Materia(id + 1, "nome", "descricao", Boolean.TRUE);

        try {
            List<Materia> materias = List.of(materia1, materia2);
            List<MateriaDTO> materiaDTOS = List.of(materiaDTO1, materiaDTO2);
            Mockito.when(materiaRepository.findAllByActive(Boolean.TRUE)).thenReturn(materias);
            Mockito.when(materiaMapper.toMateriaDTO(materia1)).thenReturn(materiaDTO1);
            Mockito.when(materiaMapper.toMateriaDTO(materia2)).thenReturn(materiaDTO2);
            List<MateriaDTO> materiasRetorno = materiaService.findAll();

            int i = 0;

            Assertions.assertEquals(materias.size(), materiasRetorno.size());

            while(i < materias.size()){
                compareMateria(materiaDTOS.get(i), materiasRetorno.get(i));
                i++;
            }
        } catch (Exception e){

        }
    }

    @Test
    public void testGravarMateria(){
        Long id = 1L;

        MateriaDTO materiaDTO = new MateriaDTO(id, "nome", "descricao");
        Materia materia = new Materia(id, "nome", "descricao", Boolean.TRUE);

        Mockito.when(materiaMapper.toMateria(materiaDTO)).thenReturn(materia);
        Mockito.when(materiaRepository.save(materia)).thenReturn(materia);

        Long retorno = materiaService.save(materiaDTO);

        Assertions.assertEquals(id, retorno);
    }

    @Test
    public void testAlterarMateriaComMateriaInexistente(){
        Long id = 1L;

        MateriaDTO materiaDTO = new MateriaDTO(id, "nome", "descricao");
        Materia materia = new Materia(id, "nome", "descricao", Boolean.TRUE);

        MateriaDTO materiaDTOAlterado = new MateriaDTO(id, "nome", "descricao");
        Materia materiaAlterado = new Materia(id, "nome", "descricao", Boolean.TRUE);

        Mockito.when(materiaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> materiaService.save(materiaDTOAlterado, id));
    }

    @Test
    public void testAlterarMateriaComMateriaExistente(){
        Long id = 1L;

        MateriaDTO materiaDTO = new MateriaDTO(id, "nome", "descricao");
        Materia materia = new Materia(id, "nome", "descricao", Boolean.TRUE);

        MateriaDTO materiaDTOAlterado = new MateriaDTO(id, "nome", "descricao");
        Materia materiaAlterado = new Materia(id, "nome", "descricao", Boolean.TRUE);

        Mockito.when(materiaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.of(materia));
        Mockito.when(materiaMapper.toMateria(materiaDTOAlterado)).thenReturn(materiaAlterado);

        Long retorno = materiaService.save(materiaDTOAlterado, id);

        Assertions.assertEquals(id, retorno);
    }

    @Test
    public void testDeleteMateriaComMateriaInexistente(){
        Long id = 1L;

        MateriaDTO materiaDTO = new MateriaDTO(id, "nome", "descricao");
        Materia materia = new Materia(id, "nome", "descricao", Boolean.TRUE);

        Mockito.when(materiaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> materiaService.deleteById(id));
    }

    @Test
    public void testDeleteMateriaComMateriaExistente(){
        Long id = 1L;

        MateriaDTO materiaDTO = new MateriaDTO(id, "nome", "descricao");
        Materia materia = new Materia(id, "nome", "descricao", Boolean.TRUE);

        Mockito.when(materiaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.of(materia));
        Mockito.when(materiaMapper.toMateriaDTO(materia)).thenReturn(materiaDTO);
        Assertions.assertEquals(Boolean.TRUE, materiaService.deleteById(id));
    }

    private void compareMateria(MateriaDTO expected, MateriaDTO actual){
        Assertions.assertAll(
                () -> Assertions.assertEquals(expected.getId(), actual.getId()),
                () -> Assertions.assertEquals(expected.getDescricao(), actual.getDescricao()),
                () -> Assertions.assertEquals(expected.getNome(), actual.getNome())
        );
    }
}
