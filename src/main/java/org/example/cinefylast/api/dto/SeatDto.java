package org.example.cinefylast.api.dto;

public record SeatDto(
        Integer id,
        int rowNumber,
        int seatNumber,
        String status
) {}
