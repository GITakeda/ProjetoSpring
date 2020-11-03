package com.example.demo.testFactory;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.ProgramaDTO;
import com.example.demo.model.Aluno;
import com.example.demo.model.Programa;

public class AlunoTestFactory {

    public static Aluno createAluno(Long id){
        Programa programa = new Programa(id, "nome", "ano", Boolean.TRUE);
        Aluno aluno = new Aluno(id, programa, "Teste", "AlunoTeste",Boolean.TRUE);

        return aluno;
    }

    public static AlunoDTO createAlunoDTO(Long id){
        ProgramaDTO programa = new ProgramaDTO(id, "nome", "ano");
        AlunoDTO aluno = new AlunoDTO(id, "AlunoTeste", "Teste", programa);

        return aluno;
    }

}
