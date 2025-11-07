package org.example.cinefylast.repo;

import org.example.cinefylast.model.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditoriumRepo extends JpaRepository<Auditorium, Long> {
    Auditorium findByName(String name);
}
