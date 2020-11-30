package com.example.demo.repository;

import com.example.demo.model.Aluno;
import com.example.demo.model.Mentor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    Optional<Mentor> findByActiveAndId(Boolean active, Long id);

    Page<Mentor> findAllByActive(Boolean active, Pageable pageable);

    List<Mentor> findAllByActive(Boolean active);
    boolean existsByIdAndActive(Long id, Boolean active);
}
