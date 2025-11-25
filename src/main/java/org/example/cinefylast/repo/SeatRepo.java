package org.example.cinefylast.repo;

import org.example.cinefylast.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepo extends JpaRepository<Seat, Long> {

    // Liefert alle Sitze des Saals mit Status für die angegebene Showtime
    @Query(value = """
      SELECT 
        s.id            AS id,
        s.row_number    AS rowNumber,
        s.seat_number   AS seatNumber,
        CASE WHEN EXISTS (
          SELECT 1 
          FROM reservation_seat rs
          WHERE rs.showtime_id = :showtimeId
            AND rs.seat_id     = s.id
        ) THEN 'reserved' ELSE 'available' END AS status
      FROM seat s
      JOIN showtime st ON st.auditorium_id = s.auditorium_id
      WHERE st.id = :showtimeId
      ORDER BY s.row_number, s.seat_number
      """, nativeQuery = true)
    List<SeatMapRow> findSeatMap(@Param("showtimeId") long showtimeId);

    interface SeatMapRow {
        Long getId();
        Integer getRowNumber();
        Integer getSeatNumber();
        String getStatus();
    }
}
