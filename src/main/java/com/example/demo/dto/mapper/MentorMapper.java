package com.example.demo.dto.mapper;

import com.example.demo.dto.MentorDTO;
import com.example.demo.model.Mentor;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MentorMapper {

//    @Mapping(target = "active",constant = "true")
    Mentor toMentor(MentorDTO mentorDTO);
    MentorDTO toMentorDTO(Mentor mentor);
}
