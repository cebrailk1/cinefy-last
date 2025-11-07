package org.example.cinefylast.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, updatable = false, length = 8)
    private String reservationCode;

    private int showtimeId;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Reservation(int i) {
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.reservationCode == null || this.reservationCode.isBlank()) {
            // einfacher Code-Generator
            this.reservationCode = UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
        }
    }
}
