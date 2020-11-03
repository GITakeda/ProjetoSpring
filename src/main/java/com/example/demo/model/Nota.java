package com.example.demo.model;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Mentoria mentoria;

    @NotNull
    @ManyToOne
    private Materia materia;

    @NotNull
    private LocalDate data;

    private Double pontuacao;

    @Column(columnDefinition = "BIT(1) default 1")
    private Boolean active;
}
