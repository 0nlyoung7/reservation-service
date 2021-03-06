package com.example.reservationservice;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ReservationRepositoryTest {
    @Autowired
    ReservationRepository reservationRepository;

    @Test
    public void testRepositoryShoudSaveFind(){

        Mono<Reservation> sReservation = reservationRepository.save(new Reservation(null, "MARIO"));

        Publisher<Reservation> foundReservation = sReservation.thenMany(reservationRepository.findAll());

        StepVerifier.create(foundReservation)
                .expectNextMatches( r-> r.getReservationName().equals("MARIO"))
                .verifyComplete();
    }
}


