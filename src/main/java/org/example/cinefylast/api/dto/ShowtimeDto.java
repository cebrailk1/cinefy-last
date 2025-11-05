package org.example.cinefylast.api.dto;

import org.example.cinefylast.model.Showtime;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ShowtimeDto(
        Integer id,
        Long tmdbId,
        Integer auditoriumId,
        LocalDateTime startsAt,
        LocalDate releaseDate,
        String title,
        String overview,
        String posterUrl,
        String backdropUrl
) {
    public static ShowtimeDto fromEntity(Showtime showtime) {
        return new ShowtimeDto(
                showtime.getId(),
                showtime.getTmdbId(),
                showtime.getAuditoriumId(),
                showtime.getStartsAt(),
                showtime.getReleaseDate(),
                showtime.getTitle(),
                showtime.getOverview(),
                showtime.getPosterUrl(),
                showtime.getBackdropUrl()
        );
    }
}
