package com.example.demo.dto.mapper;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.model.Materia;
import com.example.demo.model.Programa;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MateriaMapper {

    @Mapping(target = "active",constant = "true")
    Materia toMateria(MateriaDTO materiaDTO);
    MateriaDTO toMateriaDTO(Materia materia);
}
