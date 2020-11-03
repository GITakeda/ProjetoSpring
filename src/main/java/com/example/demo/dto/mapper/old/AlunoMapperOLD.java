//package com.example.demo.dto.mapper;
//
//import com.example.demo.dto.AlunoDTO;
//import com.example.demo.dto.mapper.ProgramaMapper;
//import com.example.demo.model.Aluno;
//import org.mapstruct.factory.Mappers;
//
//public class AlunoMapperOLD {
//
//    public static Aluno toAluno(AlunoDTO alunoDTO){
//        Aluno aluno = new Aluno();
//        ProgramaMapper programaMapper = Mappers.getMapper(ProgramaMapper.class);
//
//        aluno.setClasse(alunoDTO.getClasse());
//        aluno.setNome(alunoDTO.getNome());
//        aluno.setId(alunoDTO.getId());
//        aluno.setPrograma(programaMapper.toPrograma(alunoDTO.getProgramaDTO()));
//
//        return aluno;
//    }
//
//    public static AlunoDTO toAlunoDTO(Aluno aluno){
//        AlunoDTO alunoDTO = new AlunoDTO();
//        ProgramaMapper programaMapper = Mappers.getMapper(ProgramaMapper.class);
//
//        alunoDTO.setProgramaDTO(programaMapper.toProgramaDTO(aluno.getPrograma()));
//        alunoDTO.setClasse(aluno.getClasse());
//        alunoDTO.setNome(aluno.getNome());
//        alunoDTO.setId(aluno.getId());
//
//        return alunoDTO;
//    }
//
//}
