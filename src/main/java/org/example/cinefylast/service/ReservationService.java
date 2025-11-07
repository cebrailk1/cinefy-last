package org.example.cinefylast.service;

import lombok.RequiredArgsConstructor;
import org.example.cinefylast.api.dto.CreateReservationRequest;
import org.example.cinefylast.model.Reservation;
import org.example.cinefylast.repo.ReservationRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepo reservationRepo;

    @Transactional
    public CreateReservationRequest.Response create(CreateReservationRequest.CreateRequest req) {
        // Optional-Check:
        // if (!showtimeRepo.existsById((long) req.showtimeId())) {
        //     throw new IllegalArgumentException("Showtime not found: " + req.showtimeId());
        // }

        Reservation r = new Reservation(req.showtimeId());
        r = reservationRepo.save(r); // reservationCode & createdAt werden in @PrePersist gesetzt

        return new CreateReservationRequest.Response(
                r.getId(),
                r.getReservationCode(),
                r.getShowtimeId(),
                r.getCreatedAt()
        );
    }
}
