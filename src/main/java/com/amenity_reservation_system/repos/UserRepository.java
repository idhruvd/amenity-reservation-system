package com.amenity_reservation_system.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amenity_reservation_system.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
}
