package org.example.cinefylast.api.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CreateReservationRequest {

    // Request (vom Client an Server)
    public record CreateRequest(Long showtimeId, List<Long> seatIds) {

    }

    public record Response(Long id, String reservationCode, Long showtimeId, LocalDateTime createdAt) {}
}
