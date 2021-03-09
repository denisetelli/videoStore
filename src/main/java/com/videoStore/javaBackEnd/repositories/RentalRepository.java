package com.videoStore.javaBackEnd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videoStore.javaBackEnd.models.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long>{


}
