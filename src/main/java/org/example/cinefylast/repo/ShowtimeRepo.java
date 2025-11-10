package org.example.cinefylast.repo;

import org.example.cinefylast.model.Showtime;   // <-- WICHTIG: Showtime (nicht Auditorium!)
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowtimeRepo extends JpaRepository<Showtime, Long> {
}
