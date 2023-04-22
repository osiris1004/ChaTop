package com.backend.chatop.service.Rental;
import java.util.List;

import com.backend.chatop.model.Rental.Rental;

public interface IRentalService {
    List<Rental> getRentals();
    Rental saveRental(Rental Rental);
    Rental getRentalById(Integer id);
    Rental updateRental(Rental Rental);
    void deleteRental(Integer id);
}
