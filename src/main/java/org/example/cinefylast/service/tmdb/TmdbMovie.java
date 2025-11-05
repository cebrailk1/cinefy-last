package org.example.cinefylast.service.tmdb;

import java.time.LocalDate;

public record TmdbMovie(
        long id,
        String title,
        String overview,
        LocalDate releaseDate,
        String posterUrl,
        String backdropUrl
) {
}
