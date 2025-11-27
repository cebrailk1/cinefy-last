package org.example.cinefylast.api.dto;

public record SeatDto(
        Long id,
        Integer rowNumber,
        Integer seatNumber,
        String status
) {}