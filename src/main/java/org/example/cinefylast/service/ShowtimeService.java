package org.example.cinefylast.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cinefylast.model.Auditorium;
import org.example.cinefylast.model.Showtime;
import org.example.cinefylast.repo.AuditoriumRepo;
import org.example.cinefylast.repo.ShowtimeRepo;
import org.example.cinefylast.service.tmdb.TmdbClient;
import org.example.cinefylast.service.tmdb.TmdbMovie;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowtimeService {
    private static final int DEFAULT_SHOWTIME_HOUR = 20;
    private static final int LATEST_MOVIE_LIMIT = 10;
    private final ShowtimeRepo showtimeRepo;
    private final AuditoriumRepo auditoriumRepo;
    private final TmdbClient tmdbClient;

    @Transactional
    public List<Showtime> syncLatestShowtimes() {

        List<TmdbMovie> movies = tmdbClient.fetchLatestMovies(LATEST_MOVIE_LIMIT);
        if (CollectionUtils.isEmpty(movies)) {
            log.info("No TMDB movies retrieved for synchronisation.");
            return getShowtimesOrderedByReleaseDate();
        }

        List<Auditorium> auditoriums = auditoriumRepo.findAll(Sort.by("id"));
        if (auditoriums.isEmpty()) {
            throw new IllegalStateException("Keine Auditorien vorhanden, um Vorstellungen zuzuweisen.");
        }

        List<Showtime> updatedShowtimes = new ArrayList<>();
        int index = 0;
        for (TmdbMovie movie : movies) {
            Auditorium auditorium = auditoriums.get(index % auditoriums.size());
            LocalDate releaseDate = movie.releaseDate();
            if (releaseDate == null) {
                releaseDate = LocalDate.now().plusDays(index);
            }

            LocalDateTime startsAt = releaseDate.atTime(LocalTime.of(DEFAULT_SHOWTIME_HOUR, 0));

            Showtime showtime = showtimeRepo.findByTmdbId(Long.valueOf(movie.id()))
                    .orElseGet(Showtime::new);

            showtime.setAuditoriumId(auditorium.getId());
            showtime.setTmdbId(Long.valueOf(movie.id()));
            showtime.setTitle(movie.title());
            showtime.setOverview(movie.overview());
            showtime.setPosterUrl(movie.posterUrl());
            showtime.setBackdropUrl(movie.backdropUrl());
            showtime.setReleaseDate(releaseDate);
            showtime.setStartsAt(startsAt);

            updatedShowtimes.add(showtimeRepo.save(showtime));
            index++;
        }

        updatedShowtimes.sort(Comparator.comparing(Showtime::getReleaseDate, Comparator.nullsLast(Comparator.reverseOrder())));
        return updatedShowtimes;
    }

    @Transactional(readOnly = true)
    public boolean hasAnyShowtime() {
        return showtimeRepo.count() > 0;
    }

    @Transactional(readOnly = true)
    public List<Showtime> getShowtimesOrderedByReleaseDate() {
        return showtimeRepo.findAllByOrderByReleaseDateDesc();
    }
}
