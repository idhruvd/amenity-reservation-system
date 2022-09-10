package com.amenity_reservation_system.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amenity_reservation_system.model.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
