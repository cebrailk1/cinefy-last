package org.example.cinefylast.repo;

import org.example.cinefylast.model.Auditorium;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AuditoriumRepoTest {
    @Autowired
    private AuditoriumRepo auditoriumRepo;

    @Test
    void numberOfAuditoriums() {
        List<Auditorium> auditoriums = auditoriumRepo.findAll();
        assertThat(auditoriums).hasSize(3);
    }
    @Test
    void saal1() {
        Auditorium auditoriums = auditoriumRepo.findByName("Saal 1");
        assertThat(auditoriums).isNotNull();
        assertThat(auditoriums.getRows()).isEqualTo(15);
        assertThat(auditoriums.getSeatsPerRow()).isEqualTo(25);
    }
    @Test
    void saal2() {
        Auditorium auditoriums = auditoriumRepo.findByName("Saal 2");
        assertThat(auditoriums).isNotNull();
        assertThat(auditoriums.getRows()).isEqualTo(20);
        assertThat(auditoriums.getSeatsPerRow()).isEqualTo(25);
    }
    @Test
    void saal3() {
        Auditorium auditoriums = auditoriumRepo.findByName("Saal 3");
        assertThat(auditoriums).isNotNull();
        assertThat(auditoriums.getRows()).isEqualTo(10);
        assertThat(auditoriums.getSeatsPerRow()).as("Seats per row").isEqualTo(12);
    }

}