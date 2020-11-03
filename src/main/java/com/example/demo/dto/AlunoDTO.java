package com.example.demo.dto;

import com.example.demo.model.Aluno;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class AlunoDTO {

    private Long id;

    @NotNull(message = "Informe um nome correto")
    private String nome;

    private String classe;

    @NotNull(message = "Informe o programa")
    private ProgramaDTO programaDTO;
}
