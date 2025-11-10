package org.example.cinefylast.controller;

import lombok.RequiredArgsConstructor;
import org.example.cinefylast.api.dto.CreateReservationRequest;
import org.example.cinefylast.model.Reservation;
import org.example.cinefylast.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<CreateReservationRequest.Response> createReservation(
            @RequestBody CreateReservationRequest.CreateRequest req) {

        Reservation saved = reservationService.createReservation(req);

        return ResponseEntity.ok(new CreateReservationRequest.Response(
                saved.getId(),
                saved.getReservationCode(),
                saved.getShowtime().getId(),
                saved.getCreatedAt()
        ));
    }
}
