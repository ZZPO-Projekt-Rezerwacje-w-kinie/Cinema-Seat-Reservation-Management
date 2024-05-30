package com.cinemareservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cinemareservation.repository")
public class CinemaReservationAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(CinemaReservationAppApplication.class, args);
    }

}