package org.example.cinefylast.controller;

import lombok.RequiredArgsConstructor;
import org.example.cinefylast.api.dto.SeatDto;
import org.example.cinefylast.repo.SeatRepo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SeatController {

    private final SeatRepo seatRepo;

    @GetMapping("/seats")
    @Transactional(readOnly = true)
    public List<SeatDto> getSeats(@RequestParam Long showtimeId) {
        var rows = seatRepo.findSeatMap(showtimeId);
        return rows.stream()
                .map(r -> new SeatDto(r.getId(), r.getRowNumber(), r.getSeatNumber(), r.getStatus()))
                .toList();
    }
}
