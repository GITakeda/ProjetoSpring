package com.example.demo.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaDTO {
    private Long id;

    @NotNull
    private Long mentoria_id;

    @NotNull
    private Long materia_id;

    private LocalDate data;

    @NotNull
    private Double pontuacao;
}
