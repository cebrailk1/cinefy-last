package org.example.cinefylast.repo;

import org.example.cinefylast.model.ReservationSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationSeatRepo extends JpaRepository<ReservationSeat, Long> {
    boolean existsByShowIdAndSeatId(Long showtimeId, Long seatId);

}

