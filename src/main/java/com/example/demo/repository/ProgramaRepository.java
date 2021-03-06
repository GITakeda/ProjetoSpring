package com.example.demo.repository;

import com.example.demo.model.Aluno;
import com.example.demo.model.Programa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProgramaRepository extends JpaRepository<Programa, Long> {
    Optional<Programa> findByActiveAndId(Boolean active, Long id);

    List<Programa> findAllByActive(Boolean active);

    Page<Programa> findAllByActive(Boolean active, Pageable pageable);

    boolean existsByIdAndActive(Long id, Boolean active);
}
