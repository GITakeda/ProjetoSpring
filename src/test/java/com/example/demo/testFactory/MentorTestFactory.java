package com.example.demo.testFactory;

import com.example.demo.dto.MentorDTO;
import com.example.demo.model.Mentor;

public class MentorTestFactory {
    public static Mentor criarMentor(Long id){
        Mentor mentor = new Mentor(id, "nome", Boolean.TRUE);
        return mentor;
    }

    public static MentorDTO criarMentorDTO(Long id){
        MentorDTO mentor = new MentorDTO(id, "nome");
        return mentor;
    }
}
