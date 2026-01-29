package org.example.cinefylast.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int auditoriumId;
    private int rowNumber;
    private int seatNumber;
}
