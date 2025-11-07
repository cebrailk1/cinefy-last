package org.example.cinefylast.service.tmdb;

import java.time.LocalDate;

public record TmdbMovie(
        long id,              // lieber long, TMDB-IDs sind long
        String title,
        String overview,
        LocalDate releaseDate,
        String posterUrl,
        String backdropUrl
) {
    // Optional: kompakter Konstruktor für Validierung/Normalisierung
    public TmdbMovie {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title must not be empty");
        }
    }
}
