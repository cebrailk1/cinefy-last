package org.example.cinefylast.controller;

import lombok.RequiredArgsConstructor;
import org.example.cinefylast.model.Showtime;
import org.example.cinefylast.repo.ShowtimeRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showtime")
@RequiredArgsConstructor
public class ShowtimesController {

    private final ShowtimeRepo repo;

    @GetMapping
    public List<Showtime> getAll() {
        System.out.println("Getting all showtimes");
        return repo.findAll();
    }
}
