package org.example.cinefylast.repo;

import org.example.cinefylast.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByReservationCode(String reservationCode);
    boolean existsByReservationCode(String reservationCode);
}