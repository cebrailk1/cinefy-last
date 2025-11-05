package org.example.cinefylast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("org.example.cinefylast.repo")
@EntityScan("org.example.cinefylast.model")

public class CinefyLastApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinefyLastApplication.class, args);
        System.out.println("CinefyLastApplication started");
    }

}
