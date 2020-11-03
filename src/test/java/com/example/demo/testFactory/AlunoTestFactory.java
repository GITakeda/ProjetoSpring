package com.example.demo.testFactory;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.ProgramaDTO;
import com.example.demo.model.Aluno;
import com.example.demo.model.Programa;

public class AlunoTestFactory {

    public static Aluno createAluno(Long id, Programa programa, String classe, String nome, Boolean active){
        Aluno aluno = new Aluno(id, programa, classe, nome, active);

        return aluno;
    }

    public static AlunoDTO createAlunoDTO(Long id, String nome, String classe, ProgramaDTO programaDTO){
        AlunoDTO alunoDTO = new AlunoDTO(id, nome, classe, programaDTO);

        return alunoDTO;
    }

}
