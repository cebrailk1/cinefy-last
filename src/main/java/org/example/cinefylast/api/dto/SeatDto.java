package org.example.cinefylast.api.dto;

public record SeatDto(
        Long id,          // vorher Integer
        Integer row,
        Integer number,
        String status
) {}
