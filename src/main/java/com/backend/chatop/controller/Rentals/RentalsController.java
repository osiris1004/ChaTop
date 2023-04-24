
package com.backend.chatop.controller.Rentals;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.chatop.model.Rental.Rental;
import com.backend.chatop.service.Rental.RentalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class RentalsController {


    private final RentalService rentalService;

    @GetMapping("/rentals")
    public ResponseEntity<Object> getRentals() {
        return  ResponseRentals.generateResponse(HttpStatus.OK, rentalService.getRentals());
    }

    @GetMapping("/rentals/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }

    @PostMapping("/rentals")
    public ResponseEntity<Rental> saveRental(@RequestBody Rental rental) {
        return ResponseEntity.ok(rentalService.saveRental(rental));
    }

    @PutMapping("/rentals/{id}")
    public ResponseEntity<Rental> updateRental(@PathVariable("id") Integer id, @RequestBody Rental rental) {
        rental.setId(id);
        return ResponseEntity.ok(rentalService.updateRental(rental));
    }

    @DeleteMapping("/rentals/{id}")
    public ResponseEntity<?> deleteRental(@PathVariable("id") Integer id) {
        rentalService.deleteRental(id);
        return (ResponseEntity<?>) ResponseEntity.noContent();
    }

}
