package com.example.reservationservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class ProducerRestConfiguration {

    private final ReservationRepository reservationRepository;

    public ProducerRestConfiguration(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    @Bean
    RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route(GET("/reservations"), r->ok().body(reservationRepository.findAll(), Reservation.class) );
    }

    /**
    @Bean
    RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route(RequestPredicates.GET("/reservations"), new HandlerFunction<ServerResponse>() {
            @Override
            public Mono<ServerResponse> handle(ServerRequest serverRequest) {
                return ServerResponse.ok().body( reservationRepository.findAll(), Reservation.class);
            }
        });

    */
}