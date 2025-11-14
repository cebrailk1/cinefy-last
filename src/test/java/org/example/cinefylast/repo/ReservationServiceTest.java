package org.example.cinefylast.repo;

import org.example.cinefylast.annotations.Testing;
import org.example.cinefylast.api.dto.CreateReservationRequest;
import org.example.cinefylast.model.Reservation;
import org.example.cinefylast.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testing
public class ReservationServiceTest {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    ReservationRepo reservationRepo;

    @Test
    void createReservationSuccess() {
        List<Reservation> all = reservationRepo.findAll();
        assertThat(all).hasSize(0);
        CreateReservationRequest.CreateRequest request = new CreateReservationRequest.CreateRequest(18L);
        Reservation reservation = reservationService.createReservation(request);
        assertThat(reservation).isNotNull();
        all = reservationRepo.findAll();
        assertThat(all).hasSize(1);
    }

    @Test
    void noReservationPresent() {
        List<Reservation> all = reservationRepo.findAll();
        assertThat(all).hasSize(0);
        CreateReservationRequest.CreateRequest request = new CreateReservationRequest.CreateRequest(19L);
        Reservation reservation = reservationService.createReservation(request);
        assertThat(reservation).isNotNull();
        all = reservationRepo.findAll();
        assertThat(all).hasSize(1);
    }

}
