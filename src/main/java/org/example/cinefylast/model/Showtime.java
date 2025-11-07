package org.example.cinefylast.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "showtime")
@Getter
@Setter
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "auditorium_id")
    private Integer auditoriumId;

    @Column(name = "starts_at")
    private LocalDateTime startsAt;

    @Column(name = "tmdb_id", unique = true)
    private Long tmdbId;

    @Column(name = "title")
    private String title;

    @Column(name = "overview", columnDefinition = "TEXT")
    private String overview;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "backdrop_url")
    private String backdropUrl;

    @Column(name = "release_date")
    private LocalDate releaseDate;
}
