package org.example.cinefylast.controller;

import lombok.RequiredArgsConstructor;
import org.example.cinefylast.model.Reservation;
import org.example.cinefylast.repo.ReservationRepo;
import org.example.cinefylast.api.dto.CreateReservationRequest;
import org.example.cinefylast.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationRepo repo;
    private final ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateReservationRequest.Response create(@RequestBody CreateReservationRequest.CreateRequest createRequest ) {
        System.out.println("reservation created" +  createRequest);
        return reservationService.create(createRequest);
    }
}