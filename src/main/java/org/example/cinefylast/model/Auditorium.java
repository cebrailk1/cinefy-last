package org.example.cinefylast.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// Auditorium.java
@Data
@Entity
@Table(name = "auditorium")
public class Auditorium {
    @Id
    private int id;

    private String name;

    @Column(name = "row_count")
    private int rows;

    @Column(name = "seats_per_row")
    private int seatsPerRow;

    // getters/setters/ctor …
}
