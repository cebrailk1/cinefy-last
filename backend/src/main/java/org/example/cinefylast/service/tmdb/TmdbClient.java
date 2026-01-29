package org.example.cinefylast.service.tmdb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cinefylast.config.TmdbProperties;
import org.example.cinefylast.service.tmdb.dto.TmdbDiscoverResponse;
import org.example.cinefylast.service.tmdb.dto.TmdbMovieResult;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class TmdbClient {
    private static final DateTimeFormatter RELEASE_DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    private final RestClient.Builder restClientBuilder;
    private final TmdbProperties properties;

    public List<TmdbMovie> fetchLatestMovies(int limit) {
        String apiKey = resolveApiKey();
        RestClient restClient = restClientBuilder.baseUrl(properties.getBaseUrl()).build();

        try {
            TmdbDiscoverResponse response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/discover/movie")
                            .queryParam("sort_by", "primary_release_date.desc")
                            .queryParam("include_adult", false)
                            .queryParam("include_video", false)
                            .queryParam("language", properties.getLanguage())
                            .queryParam("region", properties.getRegion())
                            .queryParam("page", 1)
                            .queryParam("api_key", apiKey)
                            .build())
                    .retrieve()
                    .body(TmdbDiscoverResponse.class);

            List<TmdbMovieResult> results = response != null ? response.results() : List.of();
            if (results == null || results.isEmpty()) {
                return List.of();
            }

            Set<Long> seenIds = new HashSet<>();
            List<TmdbMovie> movies = new ArrayList<>();
            for (TmdbMovieResult result : results) {
                if (result == null || !seenIds.add(result.id())) {
                    continue;
                }
                TmdbMovie movie = mapToMovie(result);
                if (movie != null) {
                    movies.add(movie);
                }
            }

            movies.sort(Comparator.comparing(TmdbMovie::releaseDate, Comparator.nullsLast(Comparator.reverseOrder())));
            return movies.size() > limit ? movies.subList(0, limit) : movies;
        } catch (RestClientException ex) {
            log.error("Error while requesting movies from TMDB", ex);
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "TMDB Anfrage fehlgeschlagen", ex);
        }
    }

    private String resolveApiKey() {
        if (StringUtils.hasText(properties.getApiKey())) {
            return properties.getApiKey();
        }
        throw new ResponseStatusException(HttpStatus.PRECONDITION_REQUIRED, "TMDB API Key ist nicht konfiguriert.");
    }

    private TmdbMovie mapToMovie(TmdbMovieResult result) {
        if (!StringUtils.hasText(result.title())) {
            return null;
        }

        LocalDate releaseDate = parseReleaseDate(result.releaseDate());
        String posterUrl = buildImageUrl(result.posterPath(), properties.getPosterSize());
        String backdropUrl = buildImageUrl(result.backdropPath(), properties.getBackdropSize());

        return new TmdbMovie(
                result.id(),              // kein (int) Cast mehr
                result.title(),
                result.overview(),
                releaseDate,
                posterUrl,
                backdropUrl
        );

    }

    private LocalDate parseReleaseDate(String value) {
        if (!StringUtils.hasText(value)) {
            return null;
        }
        try {
            return LocalDate.parse(value, RELEASE_DATE_FORMATTER);
        } catch (DateTimeParseException ex) {
            log.warn("Could not parse release date '{}'", value, ex);
            return null;
        }
    }

    private String buildImageUrl(String path, String size) {
        if (!StringUtils.hasText(path)) {
            return null;
        }
        String base = properties.getImageBaseUrl();
        if (!StringUtils.hasText(base)) {
            return path;
        }
        String normalizedSize = StringUtils.hasText(size) ? size : "original";
        return String.format("%s/%s%s", base, normalizedSize, path);
    }
}
