package org.example.cinefylast.repo;

import org.example.cinefylast.model.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditoriumRepo extends JpaRepository<Auditorium, Long> {
}
