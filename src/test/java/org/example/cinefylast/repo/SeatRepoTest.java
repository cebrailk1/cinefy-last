package org.example.cinefylast.repo;

import org.example.cinefylast.model.Seat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class SeatRepoTest {
    @Autowired
    private SeatRepo seatRepo;
    @Test
    void numberOfSeatsInAuditorium1() {
        List<Seat> seats = seatRepo.findByAuditoriumId(1);
        assertThat(seats).hasSize(375);
    }
}