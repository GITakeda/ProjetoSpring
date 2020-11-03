package com.example.demo.repository;

import com.example.demo.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByActiveAndId(Boolean active, Long id);

    List<Aluno> findAllByActive(Boolean active);

    List<Aluno> findAllByActiveAndPrograma_id(Boolean active, Long programa_id);

    boolean existsByIdAndActive(Long id, Boolean active);
}
