package com.example.demo.repository;

import com.example.demo.model.Aluno;
import com.example.demo.model.Mentoria;
import com.example.demo.model.Nota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MentoriaRepository extends JpaRepository<Mentoria, Long> {
    Optional<Mentoria> findByActiveAndId(Boolean active, Long id);

    Optional<Mentoria> findByActiveAndAluno_Id(Boolean active, Long id);

    Optional<Mentoria> findByActiveAndMentor_Id(Boolean active, Long id);

    List<Mentoria> findAllByActive(Boolean active);

    Page<Mentoria> findAllByActive(Boolean active, Pageable pageable);

    boolean existsByActiveAndId(Boolean active, Long id);

    @Query(value = "SELECT id, active, mentor_id, aluno_id FROM nota WHERE active = 1 AND aluno_id = ?1 AND id != ?2", nativeQuery = true)
    Optional<Mentoria> findIgual(Long aluno_id, Long id);
}
