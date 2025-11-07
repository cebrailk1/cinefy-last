package org.example.cinefylast.web;

import lombok.RequiredArgsConstructor;
import org.example.cinefylast.model.Showtime;
import org.example.cinefylast.service.ShowtimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/showtimes/tmdb")
public class ShowtimeTmdbController {

    private final ShowtimeService showtimeService;

    @GetMapping("/latest")
    public ResponseEntity<List<Showtime>> syncAndGetLatest() {
        List<Showtime> synced = showtimeService.syncLatestShowtimes();
        return ResponseEntity.ok(synced);
    }
}
