package org.example.cinefylast.bootstrap;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cinefylast.service.ShowtimeService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BootstrapShowtimes {

    private final ShowtimeService showtimeService;

    @PostConstruct
    public void initialiseShowtimes() {
        if (showtimeService.hasAnyShowtime()) {
            return;
        }

        try {
            showtimeService.syncLatestShowtimes();
            log.info("TMDB showtimes successfully initialised.");
        } catch (Exception ex) {
            log.warn("Could not bootstrap showtimes from TMDB: {}", ex.getMessage());
        }
    }
}
