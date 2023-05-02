package com.backend.chatop.service.Rental;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.backend.chatop.errors.GlobalExceptionHandler.ResourceNotFoundException;
import com.backend.chatop.model.Rental;
import com.backend.chatop.repository.RentalRepository;
import lombok.*;


@RequiredArgsConstructor
@Service
public class RentalService implements IRentalService{

    private final RentalRepository rentalRepository;


    @Override
    public List<Rental> getRentals() {
       return rentalRepository.findAll();
    }


    @Override
    public Rental getRentalById(Integer id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        if(rental.isPresent()){
            return rental.get();
        }
        throw new ResourceNotFoundException("Not found rentals with id = " + id);
    }


    @Override
    public Rental saveRental(Rental rental) {
        return rentalRepository.save(rental);
    }


    @Override
    public Rental updateRental(Rental rental) {
        return rentalRepository.save(rental);
    }
    

    @Override
    public void deleteRental(Integer id) {
        rentalRepository.deleteById(id);
    }


}
