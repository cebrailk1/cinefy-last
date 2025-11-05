package org.example.cinefylast.repo;

import org.example.cinefylast.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShowtimeRepo extends JpaRepository<Showtime, Integer> {
    Optional<Showtime> findByTmdbId(Long tmdbId);
}
