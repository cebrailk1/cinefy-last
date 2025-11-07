package org.example.cinefylast.api.dto;

import java.time.LocalDateTime;

public class CreateReservationRequest {
    public record CreateRequest(int showtimeId) {}
    public record Response(Long id, String reservationCode, int showtimeId, LocalDateTime createdAt) {}
}
