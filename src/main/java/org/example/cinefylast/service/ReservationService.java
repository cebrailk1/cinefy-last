package org.example.cinefylast.service;

import lombok.RequiredArgsConstructor;
import org.example.cinefylast.api.dto.CreateReservationRequest;
import org.example.cinefylast.model.Reservation;
import org.example.cinefylast.model.Showtime;
import org.example.cinefylast.repo.ReservationRepo;
import org.example.cinefylast.repo.ShowtimeRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepo reservationRepo;
    private final ShowtimeRepo showtimeRepo;
    private String generateCode() {
        return java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }


    @Transactional
    public Reservation createReservation(CreateReservationRequest.CreateRequest req) {
        if (req.showtimeId() == null)
            throw new IllegalArgumentException("showtimeId darf nicht null sein");

        Showtime showtime = showtimeRepo.findById(req.showtimeId())
                .orElseThrow(() -> new IllegalArgumentException("Showtime mit ID " + req.showtimeId() + " wurde nicht gefunden."));

        LocalDateTime now = LocalDateTime.now();
        if (showtime.getStartsAt().isBefore(now.plusHours(1))) {
            throw new IllegalArgumentException("Reservierungen sind nur bis 1 Stunde vor Vorführungsbeginn möglich.");
        }
        if (showtime.getStartsAt().isAfter(now.plusDays(7))) {
            throw new IllegalArgumentException("Reservierungen sind nur bis 7 Tage im Voraus erlaubt.");
        }

        Reservation reservation = new Reservation();
        reservation.setShowtime(showtime);
        reservation.setReservationCode(generateCode());

        return reservationRepo.save(reservation);
    }}

