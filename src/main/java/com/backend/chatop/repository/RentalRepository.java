package com.backend.chatop.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.chatop.model.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {}
