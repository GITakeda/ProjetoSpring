package com.example.demo.dto.mapper;

import com.example.demo.dto.NotaDTO;
import com.example.demo.dto.mapper.decorator.NotaMapperDecorator;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Nota;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
@DecoratedWith(NotaMapperDecorator.class)
public interface NotaMapper {

    @Mapping(target = "active",constant = "true")
    Nota toNota(NotaDTO notaDTO) throws NotFoundException;

    @Mappings({
            @Mapping(target = "mentoria_id", source = "nota.mentoria.id"),
            @Mapping(target = "materia_id", source = "nota.materia.id")
    })
    NotaDTO toNotaDTO(Nota nota);
}
