package com.example.demo.dto;


import com.example.demo.model.Programa;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NotNull
public class ProgramaDTO {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String ano;
}
