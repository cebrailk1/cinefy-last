package org.example.cinefylast.api.dto;

import java.time.LocalDateTime;

public class CreateReservationRequest {

    // Request (vom Client an Server)
    public record CreateRequest(Long showtimeId) {}

    // Response (vom Server an Client)
    public record Response(Long id, String reservationCode, Long showtimeId, LocalDateTime createdAt) {}
}
