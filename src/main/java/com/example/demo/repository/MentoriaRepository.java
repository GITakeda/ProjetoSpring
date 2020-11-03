package com.example.demo.repository;

import com.example.demo.model.Mentoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MentoriaRepository extends JpaRepository<Mentoria, Long> {
    Optional<Mentoria> findByActiveAndId(Boolean active, Long id);

    Optional<Mentoria> findByActiveAndAluno_Id(Boolean active, Long id);

    Optional<Mentoria> findByActiveAndMentor_Id(Boolean active, Long id);

    List<Mentoria> findAllByActive(Boolean active);

    boolean existsByActiveAndId(Boolean active, Long id);
}
