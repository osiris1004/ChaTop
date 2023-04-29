package com.backend.chatop.service.Rental;
import java.util.List;

import com.backend.chatop.model.Rental;

public interface IRentalService {
    List<Rental> getRentals();
    Rental saveRental(Rental rental);
    Rental getRentalById(Integer id);
    Rental updateRental(Rental rental);
    void deleteRental(Integer id);
}
