package org.example.cinefylast.repo;

import org.example.cinefylast.model.ReservationSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationSeatRepo extends JpaRepository<ReservationSeat, Long> {

    boolean existsByShowtimeIdAndSeatId(Long showtimeId, Long seatId);

    @Query("select rs.seatId from ReservationSeat rs where rs.showtimeId = :showtimeId")
    List<Long> findSeatIdsByShowtimeId(Long showtimeId);
}
