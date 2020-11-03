package com.example.demo.service;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.ProgramaDTO;
import com.example.demo.dto.mapper.AlunoMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Aluno;
import com.example.demo.model.Programa;
import com.example.demo.repository.AlunoRepository;
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
public class AlunoServiceTest {

    @Mock
    AlunoRepository alunoRepository;

    @Mock
    ProgramaService programaService;

    @Mock
    AlunoMapper alunoMapper;

    @InjectMocks
    AlunoService alunoService;

    @Test
    public void testGetAluno() {
        Long id = 1l;

        ProgramaDTO programaDTO = new ProgramaDTO(id, "nome", "ano");
        AlunoDTO alunoDTO = new AlunoDTO(id, "AlunoTeste", "Teste", programaDTO);

        Programa programa = new Programa(id, programaDTO.getNome(), programaDTO.getAno(), Boolean.TRUE);
        Aluno aluno = new Aluno(id, programa, alunoDTO.getClasse(), alunoDTO.getNome(), Boolean.TRUE);


        Mockito.when(alunoRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.of(aluno));

        AlunoDTO alunoDTORetorno = alunoService.findById(id);

        compareAluno(alunoDTO, alunoDTORetorno);
    }

    @Test
    public void testGetAllAlunos(){
        Long id = 1l;

        ProgramaDTO programaDTO = new ProgramaDTO(id, "nome", "ano");
        AlunoDTO alunoDTO1 = new AlunoDTO(id, "AlunoTeste", "Teste", programaDTO);
        AlunoDTO alunoDTO2 = new AlunoDTO(id, alunoDTO1.getNome(), alunoDTO1.getClasse(),  programaDTO);

        Programa programa = new Programa(id, programaDTO.getNome(), programaDTO.getAno(), Boolean.TRUE);
        Aluno aluno1 = new Aluno(id, programa, alunoDTO1.getClasse(), alunoDTO1.getNome(), Boolean.TRUE);
        Aluno aluno2 = new Aluno(id + 1, programa, alunoDTO1.getClasse(), alunoDTO1.getNome(), Boolean.TRUE);


        List<Aluno> alunos = List.of(aluno1, aluno2);
        List<AlunoDTO> alunoDTOS = List.of(alunoDTO1, alunoDTO2);
        Mockito.when(alunoRepository.findAllByActive(Boolean.TRUE)).thenReturn(alunos);

        List<AlunoDTO> alunosRetorno = alunoService.getAlunos();

        int i = 0;

        Assertions.assertEquals(alunos.size(), alunosRetorno.size());

        while(i < alunos.size()){
            compareAluno(alunoDTOS.get(i), alunosRetorno.get(i));
            i++;
        }
    }

    @Test
    public void testGravarAlunoComPrograma(){
        Long id = 1l;

        ProgramaDTO programaDTO = new ProgramaDTO(id, "nome", "ano");
        AlunoDTO alunoDTO = new AlunoDTO(id, "AlunoTeste", "Teste", programaDTO);

        Mockito.when(programaService.findById(alunoDTO.getProgramaDTO().getId())).thenReturn(programaDTO);

        Long idRetorno = alunoService.save(alunoDTO);
        Assertions.assertEquals(id, idRetorno);
    }

    @Test
    public void testGravarAlunoComProgramaInexistente(){
        Long id = 1l;

        Programa programa = new Programa(id, "nome", "ano", Boolean.TRUE);
        Aluno aluno = new Aluno(id, programa, "Teste", "AlunoTeste",Boolean.TRUE);

        ProgramaDTO programaDTO = new ProgramaDTO(id, "nome", "ano");
        AlunoDTO alunoDTO = new AlunoDTO(id, "AlunoTeste", "Teste", null);

        Assertions.assertThrows(NotFoundException.class, () -> alunoService.save(alunoDTO));
    }

    @Test
    public void testGravarAlunoSemPrograma(){
        Long id = 1l;

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(id);
        alunoDTO.setNome("nome");
        alunoDTO.setClasse("classe");

        Assertions.assertThrows(Exception.class, () -> alunoService.save(alunoDTO));
    }

    @Test
    public void testAlterarAlunoComAlunoExistente(){
        var id = 1l;

        ProgramaDTO programaDTO = new ProgramaDTO(id, "nome", "ano");
        AlunoDTO alunoDTO = new AlunoDTO(id, "AlunoTeste", "Teste", programaDTO);


        Assertions.assertThrows(Exception.class, () -> alunoService.save(alunoDTO, id));

    }

    @Test
    public void testDeleteAlunoComAlunoExistente(){
        Long id = 1l;

        ProgramaDTO programaDTO = new ProgramaDTO(id, "nome", "ano");
        AlunoDTO alunoDTO = new AlunoDTO(id, "AlunoTeste", "Teste", programaDTO);

        Programa programa = new Programa(id, programaDTO.getNome(), programaDTO.getAno(), Boolean.TRUE);
        Aluno aluno = new Aluno(id, programa, alunoDTO.getClasse(), alunoDTO.getNome(), Boolean.TRUE);


        Mockito.when(alunoRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.of(aluno));
        Assertions.assertEquals(Boolean.TRUE, alunoService.deleteById(id));
    }

    @Test
    public void testDeleteAlunoComAlunoInexistente(){
        var id = 1l;

        ProgramaDTO programaDTO = new ProgramaDTO(id, "nome", "ano");
        AlunoDTO alunoDTO = new AlunoDTO(id, "AlunoTeste", "Teste", programaDTO);

        Mockito.when(alunoRepository.findByActiveAndId(Boolean.TRUE, id)).thenReturn(Optional.empty());

        Assertions.assertEquals(Boolean.FALSE, alunoService.deleteById(id));
    }

    private void compareAluno(AlunoDTO expected, AlunoDTO actual){
        Assertions.assertAll(
                () -> Assertions.assertEquals(expected.getId(), actual.getId()),
                () -> Assertions.assertEquals(expected.getClasse(), actual.getClasse()),
                () -> Assertions.assertEquals(expected.getNome(), actual.getNome())
        );

        comparePrograma(expected.getProgramaDTO(), actual.getProgramaDTO());
    }

    private void compareAluno(Aluno expected, Aluno actual){
        Assertions.assertAll(
                () -> Assertions.assertEquals(expected.getId(), actual.getId()),
                () -> Assertions.assertEquals(expected.getClasse(), actual.getClasse()),
                () -> Assertions.assertEquals(expected.getNome(), actual.getNome())
        );

        comparePrograma(expected.getPrograma(), actual.getPrograma());
    }

    private void comparePrograma(ProgramaDTO expected, ProgramaDTO actual){
        Assertions.assertAll(
                () -> Assertions.assertEquals(expected.getId(), actual.getId()),
                () -> Assertions.assertEquals(expected.getNome(), actual.getNome()),
                () -> Assertions.assertEquals(expected.getAno(), actual.getAno())
        );
    }

    private void comparePrograma(Programa expected, Programa actual){
        Assertions.assertAll(
                () -> Assertions.assertEquals(expected.getId(), actual.getId()),
                () -> Assertions.assertEquals(expected.getNome(), actual.getNome()),
                () -> Assertions.assertEquals(expected.getAno(), actual.getAno())
        );
    }
}
