
package com.backend.chatop.controller.Rentals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backend.chatop.FileUtils.ImageUtils;
import com.backend.chatop.model.Attachment;
import com.backend.chatop.model.Rental;
import com.backend.chatop.service.Attachment.AttachmentService;
import com.backend.chatop.service.Rental.RentalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class RentalsController {

    private final RentalService rentalService;
    private final AttachmentService attachmentService;
 
    //!GET_ALL
    @GetMapping("/rentals")
    public ResponseEntity<Object> getRentals() {
        return ResponseRentals.generateResponse(HttpStatus.OK, rentalService.getRentals());
    }

    //!GET_BY_ID
    @GetMapping("/rentals/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }

    //!ADD
    @PostMapping("/rentals")
    public ResponseEntity<Rental> saveRental(
        // @RequestPart("data") Rental rental,
        // @RequestPart("file") MultipartFile file
        @ModelAttribute RequestRentals rental
        ){
        //**File */
        Attachment attachment = attachmentService.saveAttachment(rental.getPicture());
        Rental newRental = new Rental(rental.getName(), rental.getSurface(), attachment.getAttachmentUrl(),  rental.getPrice(), rental.getDescription(), rental.getOwner_id(), rental.getCreated_at(), attachment);
        return ResponseEntity.ok(rentalService.saveRental(newRental));
    }

     //!UPDATE
    @PutMapping("/rentals/{id}")
    public ResponseEntity<Rental> updateRental(
             @PathVariable("id") Integer id,
            // @RequestPart("data") Rental rental,
            // @RequestPart("file") MultipartFile file
            @ModelAttribute RequestRentals rental
            ) {

 
        Attachment attachment = attachmentService.getAttachmentById(id);
        Rental updateRental = new Rental(id, rental.getName(), rental.getSurface(), attachment.getAttachmentUrl(),  rental.getPrice(), rental.getDescription(), rental.getOwner_id(), rental.getCreated_at(), attachment);
        return ResponseEntity.ok(rentalService.updateRental(updateRental));

    }

    //!DELETE
    @DeleteMapping("/rentals/{id}")
    public ResponseEntity<?> deleteRental(@PathVariable("id") Integer id) {
        rentalService.deleteRental(id);
        return (ResponseEntity<?>) ResponseEntity.noContent();
    }


    //!GET_PICTURE
    @GetMapping("/attachment/{id}")
    public ResponseEntity<?>  getImageByName(@PathVariable("id") Integer id){
        byte[] image = attachmentService.getImageByAttachmentId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
}
