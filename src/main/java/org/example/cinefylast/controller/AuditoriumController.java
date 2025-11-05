package org.example.cinefylast.controller;

import lombok.RequiredArgsConstructor;
import org.example.cinefylast.model.Auditorium;
import org.example.cinefylast.repo.AuditoriumRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auditoriums")
@RequiredArgsConstructor
public class AuditoriumController {

    private final AuditoriumRepo repo;

    @GetMapping
    public List<Auditorium> getAll() {
        System.out.println("Getting all auditoriums");
        return repo.findAll();
    }
}
