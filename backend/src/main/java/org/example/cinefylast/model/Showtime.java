package org.example.cinefylast.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "showtime")
public class Showtime {

    // Getter & Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "auditorium_id", nullable = false)
    private Auditorium auditorium; // falls du die Klasse hast

    @Column(name = "starts_at", nullable = false)
    private LocalDateTime startsAt;

}
