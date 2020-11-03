package com.example.demo.service;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.dto.mapper.MateriaMapper;
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
    MateriaRepository materiaRepository;

    @Mock
    MateriaMapper materiaMapperMock;

    @InjectMocks
    MateriaService materiaService;

    @Test
    public void testGetMateria(){
        Long id = 1L;

        MateriaDTO materiaDTO = new MateriaDTO(id, "nome", "descricao");
        Materia materia = new Materia(id, "nome", "descricao", Boolean.TRUE);

        Mockito.when(materiaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.of(materia));
        Mockito.when(materiaMapperMock.toMateriaDTO(materia)).thenReturn(materiaDTO);

        compareMateria(materiaDTO, materiaService.findById(id));
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
            Mockito.when(materiaMapperMock.toMateriaDTO(materia1)).thenReturn(materiaDTO1);
            Mockito.when(materiaMapperMock.toMateriaDTO(materia2)).thenReturn(materiaDTO2);
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

    public void testGravarMateria(){

    }

    private void compareMateria(MateriaDTO expected, MateriaDTO actual){
        Assertions.assertAll(
                () -> Assertions.assertEquals(expected.getId(), actual.getId()),
                () -> Assertions.assertEquals(expected.getDescricao(), actual.getDescricao()),
                () -> Assertions.assertEquals(expected.getNome(), actual.getNome())
        );
    }

    private void compareMateria(Materia expected, Materia actual){
        Assertions.assertAll(
                () -> Assertions.assertEquals(expected.getId(), actual.getId()),
                () -> Assertions.assertEquals(expected.getDescricao(), actual.getDescricao()),
                () -> Assertions.assertEquals(expected.getNome(), actual.getNome())
        );
    }

}
