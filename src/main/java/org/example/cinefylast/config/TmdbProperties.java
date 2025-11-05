package org.example.cinefylast.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "tmdb")
public class TmdbProperties {
    private String apiKey;
    private String baseUrl = "https://api.themoviedb.org/3";
    private String imageBaseUrl = "https://image.tmdb.org/t/p";
    private String posterSize = "w500";
    private String backdropSize = "w780";
    private String language = "de-DE";
    private String region = "DE";
}
