package com.example.demo.testFactory;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.dto.MentoriaDTO;
import com.example.demo.dto.NotaDTO;
import com.example.demo.model.Materia;
import com.example.demo.model.Mentoria;
import com.example.demo.model.Nota;

import java.time.LocalDate;

public class NotaTestFactory {

    public static Nota createNota(Long id){
        Mentoria mentoria = MentoriaTestFactory.createMentoria(id);
        Materia materia = new Materia(id, "nome", "descricao", Boolean.TRUE);

        return new Nota(id, mentoria, materia, LocalDate.now(), 10d, Boolean.TRUE);
    }

    public static NotaDTO createNotaDTO(Long id){
        MentoriaDTO mentoriaDTO = MentoriaTestFactory.createMentoriaDTO(id);
        MateriaDTO materiaDTO = new MateriaDTO(id, "nome", "descricao");

        return new NotaDTO(id, mentoriaDTO, materiaDTO, LocalDate.now(), 10d);
    }

}
