package com.example.demo.repository;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.model.Aluno;
import com.example.demo.model.Materia;
import com.example.demo.model.Mentoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
    List<Materia> findAllByActive(Boolean active);

    Page<Materia> findAllByActive(Boolean active, Pageable pageable);

    Optional<Materia> findByActiveAndId(Boolean active, Long id);

    boolean existsByIdAndActive(Long id, Boolean active);
}
