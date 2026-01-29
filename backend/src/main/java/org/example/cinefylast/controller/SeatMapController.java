package org.example.cinefylast.controller;

import lombok.RequiredArgsConstructor;
import org.example.cinefylast.model.Seat;
import org.example.cinefylast.repo.SeatRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatMapController {

    private final SeatRepo repo;

    @GetMapping
    public List<Seat> getAll() {
        System.out.println("Getting all seats");
        return repo.findAll();
    }
}
