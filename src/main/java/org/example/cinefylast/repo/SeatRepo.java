package org.example.cinefylast.repo;

import org.example.cinefylast.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepo extends JpaRepository<Seat, Integer> {
    List<Seat> findByAuditoriumId(int auditoriumId);
}
