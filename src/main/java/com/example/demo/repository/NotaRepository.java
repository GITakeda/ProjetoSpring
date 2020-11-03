package com.example.demo.repository;

import com.example.demo.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    Optional<Nota> findByActiveAndId(Boolean active, Long id);

    Optional<Nota> findByMateria_idAndMentoria_idAndData(Long materia_id, Long mentoria_id, LocalDate data);

    @Query(value = "SELECT id, active, data, materia_id, mentoria_id, pontuacao FROM nota WHERE active = 1 AND materia_id = ?1 AND mentoria_id = ?2 AND data = ?3 AND id != ?4", nativeQuery = true)
    Optional<Nota> findIgual(Long materia_id, Long mentoria_id, LocalDate data, Long id);

    List<Nota> findAllByActive(Boolean active);

    List<Nota> findAllByActiveAndMentoria_id(Boolean active, Long mentoria_id);

    List<Nota> findAllByActiveAndMateria_id(Boolean active, Long materia_id);

    boolean existsByIdAndActive(Long id, Boolean active);
}
