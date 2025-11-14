package org.example.cinefylast.service;

import lombok.RequiredArgsConstructor;
import org.example.cinefylast.api.dto.CreateReservationRequest;
import org.example.cinefylast.model.Reservation;
import org.example.cinefylast.model.ReservationSeat;
import org.example.cinefylast.model.Showtime;
import org.example.cinefylast.repo.ReservationRepo;
import org.example.cinefylast.repo.ReservationSeatRepo;
import org.example.cinefylast.repo.ShowtimeRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepo reservationRepo;
    private final ShowtimeRepo showtimeRepo;
    private final ReservationSeatRepo reservationSeatRepo;

    private String generateCode() {
        return java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }


    @Transactional
    public Reservation createReservation(CreateReservationRequest.CreateRequest req) {
        if (req.showtimeId() == null)
            throw new IllegalArgumentException("showtimeId darf nicht null sein");
        if (req.seatIds() == null || req.seatIds().isEmpty())
            throw new IllegalArgumentException("Es muss mindestens ein Sitz ausgewählt werden.");

        Showtime showtime = showtimeRepo.findById(req.showtimeId())
                .orElseThrow(() -> new IllegalArgumentException("Showtime mit ID " + req.showtimeId() + " wurde nicht gefunden."));

        LocalDateTime now = LocalDateTime.now();
        if (showtime.getStartsAt().isBefore(now.plusHours(1))) {
            throw new IllegalArgumentException("Reservierungen sind nur bis 1 Stunde vor Vorführungsbeginn möglich.");
        }
        if (showtime.getStartsAt().isAfter(now.plusDays(7))) {
            throw new IllegalArgumentException("Reservierungen sind nur bis 7 Tage im Voraus erlaubt.");
        }
        for (Long seatId : req.seatIds()) {
            if (reservationSeatRepo.existsByShowIdAndSeatId(showtime.getId(), seatId)) {
                throw new IllegalStateException("Sitz " + seatId + " ist bereits reserviert.");
            }
        }

        Reservation reservation = new Reservation();
        reservation.setShowtime(showtime);
        reservation.setReservationCode(generateCode());
        reservation = reservationRepo.save(reservation);

        for (Long seatId : req.seatIds()) {
            ReservationSeat rs = new ReservationSeat();
            rs.setReservationId(reservation.getId());
            rs.setShowId(showtime.getId());
            rs.setSeatId(seatId);
            reservationSeatRepo.save(rs);
        }

        return reservation;
    }

}
