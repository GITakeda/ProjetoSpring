package com.example.demo.service;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.MentorDTO;
import com.example.demo.dto.ProgramaDTO;
import com.example.demo.dto.mapper.ProgramaMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.WrongArgumentException;
import com.example.demo.model.Mentor;
import com.example.demo.model.Programa;
import com.example.demo.repository.ProgramaRepository;
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
public class ProgramaServiceTest {

    @Mock
    private ProgramaRepository programaRepository;

    @Mock
    private ProgramaMapper programaMapper;

    @Mock
    private AlunoService alunoService;

    @InjectMocks
    private ProgramaService programaService;

    @Test
    public void testGetProgramaComProgramaExistente(){
        Long id = 1l;

        ProgramaDTO programaDTO = new ProgramaDTO(id, "nome", "ano");
        Programa programa = new Programa(id, "nome", "ano", Boolean.TRUE);

        Mockito.when(programaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.of(programa));
        Mockito.when(programaMapper.toProgramaDTO(programa)).thenReturn(programaDTO);

        comparePrograma(programaDTO, programaService.findById(id));
    }

    @Test
    public void testGetProgramaComProgramaInexistente(){
        Long id = 1l;

        ProgramaDTO programaDTO = new ProgramaDTO(id, "nome", "ano");
        Programa programa = new Programa(id, "nome", "ano", Boolean.TRUE);

        Mockito.when(programaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> programaService.findById(id));
    }

    @Test
    public void testGetProgramas(){
        Long id = 1l;

        ProgramaDTO programaDTO1 = new ProgramaDTO(id, "nome", "ano");
        Programa programa1 = new Programa(id, "nome", "ano", Boolean.TRUE);

        ProgramaDTO programaDTO2 = new ProgramaDTO(id + 1, "nome", "ano");
        Programa programa2 = new Programa(id + 1, "nome", "ano", Boolean.TRUE);

        List<ProgramaDTO> programaDTOList = List.of(programaDTO1, programaDTO2);
        List<Programa> programaList = List.of(programa1, programa2);

        Mockito.when(programaRepository.findAllByActive(Boolean.TRUE)).thenReturn(programaList);
        Mockito.when(programaMapper.toProgramaDTO(programa1)).thenReturn(programaDTO1);
        Mockito.when(programaMapper.toProgramaDTO(programa2)).thenReturn(programaDTO2);

        List<ProgramaDTO> retorno = programaService.findAll();

        Long i = 0l;

        while(i < retorno.size()){
            comparePrograma(programaDTOList.get(i.intValue()), retorno.get(i.intValue()));
            i++;
        }
    }

    @Test
    public void testGravarPrograma(){
        Long id = 1l;

        ProgramaDTO programaDTO = new ProgramaDTO(id, "nome", "ano");
        Programa programa = new Programa(id, "nome", "ano", Boolean.TRUE);

        Mockito.when(programaMapper.toPrograma(programaDTO)).thenReturn(programa);

        Assertions.assertEquals(id, programaService.save(programaDTO));
    }

    @Test
    public void testAlterarProgramaComProgramaInexistente(){
        Long id = 1l;

        ProgramaDTO programaDTO = new ProgramaDTO(id, "nome", "ano");
        Programa programa = new Programa(id, "nome", "ano", Boolean.TRUE);

        Assertions.assertThrows(NotFoundException.class, () -> programaService.save(programaDTO, id));
    }

    @Test
    public void testAlterarProgramaComProgramaExistente(){
        Long id = 1l;

        ProgramaDTO programaDTO = new ProgramaDTO(id, "nome", "ano");
        Programa programa = new Programa(id, "nome", "ano", Boolean.TRUE);

        Mockito.when(programaRepository.existsByIdAndActive(id, Boolean.TRUE)).thenReturn(true);
        Mockito.when(programaMapper.toPrograma(programaDTO)).thenReturn(programa);

        Assertions.assertEquals(id, programaService.save(programaDTO, id));
    }

    @Test
    public void testDeleteProgramaComProgramaInexistente(){
        Long id = 1L;

        ProgramaDTO programaDTO = new ProgramaDTO(id, "nome", "ano");
        Programa programa = new Programa(id, "nome", "ano", Boolean.TRUE);

        Mockito.when(programaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> programaService.deleteById(id));
    }

    @Test
    public void testDeleteProgramaComProgramaExistenteComAlunoInexistente(){
        Long id = 1L;

        ProgramaDTO programaDTO = new ProgramaDTO(id, "nome", "ano");
        Programa programa = new Programa(id, "nome", "ano", Boolean.TRUE);

        Mockito.when(programaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.of(programa));

        Assertions.assertEquals(Boolean.TRUE, programaService.deleteById(id));
    }

    @Test
    public void testDeleteProgramaComProgramaExistenteComAlunoExistente(){
        Long id = 1L;

        ProgramaDTO programaDTO = new ProgramaDTO(id, "nome", "ano");
        Programa programa = new Programa(id, "nome", "ano", Boolean.TRUE);

        Mockito.when(programaRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.of(programa));
        Mockito.when(alunoService.getAlunosByPrograma(id)).thenReturn(List.of(new AlunoDTO()));

        Assertions.assertThrows(WrongArgumentException.class, () -> programaService.deleteById(id));
    }

    protected void comparePrograma(ProgramaDTO expected, ProgramaDTO actual){
        Assertions.assertAll(
                () -> Assertions.assertEquals(expected.getId(), actual.getId()),
                () -> Assertions.assertEquals(expected.getNome(), actual.getNome()),
                () -> Assertions.assertEquals(expected.getAno(), actual.getAno())
        );
    }

    protected void comparePrograma(Programa expected, Programa actual){
        Assertions.assertAll(
                () -> Assertions.assertEquals(expected.getId(), actual.getId()),
                () -> Assertions.assertEquals(expected.getNome(), actual.getNome()),
                () -> Assertions.assertEquals(expected.getAno(), actual.getAno())
        );
    }

}
