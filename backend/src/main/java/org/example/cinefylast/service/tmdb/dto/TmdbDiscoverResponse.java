package org.example.cinefylast.service.tmdb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TmdbDiscoverResponse(
        @JsonProperty("results") List<TmdbMovieResult> results
) {
}
