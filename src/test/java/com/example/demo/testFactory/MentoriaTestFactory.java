package com.example.demo.testFactory;


import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.MentorDTO;
import com.example.demo.dto.MentoriaDTO;
import com.example.demo.model.Aluno;
import com.example.demo.model.Mentor;
import com.example.demo.model.Mentoria;

public class MentoriaTestFactory {

    public static Mentoria createMentoria(Long id){
        Aluno aluno = AlunoTestFactory.createAluno(id);
        AlunoDTO alunoDTO = AlunoTestFactory.createAlunoDTO(id);

        Mentor mentor = MentorTestFactory.criarMentor(id);
        MentorDTO mentorDTO = MentorTestFactory.criarMentorDTO(id);

        return new Mentoria(id, aluno, mentor, Boolean.TRUE);
    }

    public static MentoriaDTO createMentoriaDTO(Long id){
        Aluno aluno = AlunoTestFactory.createAluno(id);
        AlunoDTO alunoDTO = AlunoTestFactory.createAlunoDTO(id);

        Mentor mentor = MentorTestFactory.criarMentor(id);
        MentorDTO mentorDTO = MentorTestFactory.criarMentorDTO(id);

        return new MentoriaDTO(id, id, id);
    }

}
