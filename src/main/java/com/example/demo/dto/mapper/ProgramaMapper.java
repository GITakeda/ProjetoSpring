package com.example.demo.dto.mapper;

import com.example.demo.dto.ProgramaDTO;
import com.example.demo.model.Programa;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProgramaMapper {

//    @Mapping(target = "active",constant = "true")
    Programa toPrograma(ProgramaDTO programaDTO);
    ProgramaDTO toProgramaDTO(Programa programa);
}
