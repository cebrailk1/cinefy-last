package org.example.cinefylast.controller;

import lombok.RequiredArgsConstructor;
import org.example.cinefylast.api.dto.SeatDto;
import org.example.cinefylast.model.Showtime;
import org.example.cinefylast.repo.SeatRepo;
import org.example.cinefylast.repo.ShowtimeRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SeatController {

    private final ShowtimeRepo showtimeRepo;
    private final SeatRepo seatRepo;

    @GetMapping("/seats")
    public List<SeatDto> getSeats(@RequestParam Long showtimeId) {

        Showtime st = showtimeRepo.findById(showtimeId)
                .orElseThrow(() -> new IllegalArgumentException("Showtime nicht gefunden"));

        int auditoriumId = st.getAuditorium().getId();

        return seatRepo.findByAuditoriumId(auditoriumId)
                .stream()
                .map(s -> new SeatDto(
                        s.getId(),
                        s.getRowNumber(),
                        s.getSeatNumber(),
                        "available"
                ))
                .toList();
    }
}
