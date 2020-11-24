package com.example.demo.dto.mapper;

import com.example.demo.dto.MentoriaDTO;
import com.example.demo.dto.mapper.decorator.MentoriaMapperDecorator;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Mentoria;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
@DecoratedWith(MentoriaMapperDecorator.class)
public interface MentoriaMapper {

    MentoriaDTO toMentoriaDTO(Mentoria mentoria);

    @Mapping(target = "active",constant = "true")
    Mentoria toMentoria(MentoriaDTO mentoriaDTO) throws NotFoundException;
}
