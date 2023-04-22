package com.backend.chatop.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.chatop.model.Rental.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer>{}
