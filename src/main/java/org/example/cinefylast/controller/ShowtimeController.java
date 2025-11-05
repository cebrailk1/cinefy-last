package org.example.cinefylast.controller;

import lombok.RequiredArgsConstructor;
import org.example.cinefylast.api.dto.ShowtimeDto;
import org.example.cinefylast.service.ShowtimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    @GetMapping
    public List<ShowtimeDto> getShowtimes() {
        return showtimeService.getShowtimesOrderedByReleaseDate()
                .stream()
                .map(ShowtimeDto::fromEntity)
                .toList();
    }

    @GetMapping("/tmdb/latest")
    public List<ShowtimeDto> syncLatestShowtimes() {
        return showtimeService.syncLatestShowtimes()
                .stream()
                .map(ShowtimeDto::fromEntity)
                .toList();
    }
}
