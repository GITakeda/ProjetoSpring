//package com.example.demo.dto.mapper;
//
//import com.example.demo.dto.NotaDTO;
//import com.example.demo.model.Nota;
//
//public class NotaMapperOLD {
//
//    public static Nota toNota(NotaDTO notaDTO){
//        Nota nota = new Nota();
//        nota.setId(notaDTO.getId());
//        nota.setData(notaDTO.getData());
//        nota.setPontuacao(notaDTO.getPontuacao());
//
//        return nota;
//    }
//
//    public static NotaDTO toNotaDTO(Nota nota){
//        NotaDTO notaDTO = new NotaDTO();
//        notaDTO.setId(nota.getId());
//        notaDTO.setData(nota.getData());
//        notaDTO.setMateria_id(nota.getMateria().getId());
//        notaDTO.setMentoria_id(nota.getMentoria().getId());
//        notaDTO.setPontuacao(nota.getPontuacao());
//
//        return notaDTO;
//    }
//}
