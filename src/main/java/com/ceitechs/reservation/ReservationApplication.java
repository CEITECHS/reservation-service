package com.ceitechs.reservation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@EnableDiscoveryClient
@SpringBootApplication
public class ReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationApplication.class, args);
	}

    @Bean
    CommandLineRunner run(){
        return strings -> {
            Arrays.asList("Iddy","Magohe","Pango letu").forEach(System.out::println);
        };
    }

}

@Getter
@Setter
class Reservation {

    private Long id;
    private String reservationName;

    public Reservation(String reservationName) {
        this.reservationName = reservationName;
        this.id = System.currentTimeMillis();
    }
}

@RefreshScope
@RestController class ReservationRestController{

    @Value("${reservation.name}")
    private String names;

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    Collection<Reservation> getReservation(){
        return Arrays.asList(names.split(",")).stream().map(Reservation::new).collect(Collectors.toList());

    }

}