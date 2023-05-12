
package com.backend.chatop.controller.Rentals;

import java.util.Date;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.chatop.errors.GlobalExceptionHandler.ErrorMessage;
import com.backend.chatop.errors.GlobalExceptionHandler.MyAuthenticationEntryPoint.MyAuthenticationEntryPoint;
import com.backend.chatop.model.Attachment;
import com.backend.chatop.model.Rental;
import com.backend.chatop.service.Attachment.AttachmentService;
import com.backend.chatop.service.Rental.RentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")

@Tag(name = "Rentals", description = "Rentals management APIs")
public class RentalsController {

    private final RentalService rentalService;
    private final AttachmentService attachmentService;

    // !GET_ALL
    @Operation(
        summary = "Retrieve {Rental} List", 
        description = "Get an list of {Rental}. The response is an array of {Rental} object.", 
        tags = {"{Rental}", "get" })
        @ApiResponses({
                @ApiResponse(
                    responseCode = "200", 
                    description = "Found the {Rental} matching this Id",
                    content = {@Content(
                        array = @ArraySchema(schema = @Schema(implementation = Rental.class)), 
                        mediaType = "application/json") }),
             
                @ApiResponse(
                    responseCode = "401", 
                    description = "Unauthorized",
                    content = {@Content(schema = @Schema(implementation = MyAuthenticationEntryPoint.class)) }),
        })
    @GetMapping("/rentals")
    public ResponseEntity<Object> getRentals() {
        return ResponseRentals.generateResponse(HttpStatus.OK, rentalService.getRentals());
    }

    // !GET_BY_ID
    @Operation(
        summary = "Retrieve {Rental} by Id", 
        description = "Get a {Rental} object by specifying its id. The response is {Rental} object.", 
        tags = {"{Rental}", "get" })
        @ApiResponses({
                @ApiResponse(
                    responseCode = "200", 
                    description = "Found the {Rental} matching this Id",
                    content = {@Content(schema = @Schema(implementation = Rental.class), mediaType = "application/json") }),
                @ApiResponse(
                    responseCode = "404", 
                    description = "{Rental} Not Found", 
                    content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}),

                @ApiResponse(
                    responseCode = "401", 
                    description = "Unauthorized",
                    content = {@Content(schema = @Schema(implementation = MyAuthenticationEntryPoint.class)) }),
        })

    @GetMapping("/rentals/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }

    // !ADD
    @Operation(
        summary = "Add a new {Rental}", 
        description = "Create a {Rental} object by passing the input. The response is {Rental} object.", 
        tags = {"{Rental}", "post" })
        @ApiResponses({
                @ApiResponse(
                    responseCode = "200", 
                    description = "The Added {Rental} was successfully created",
                    content = {@Content(schema = @Schema(implementation = Rental.class), mediaType = "application/json") }),
                @ApiResponse(
                    responseCode = "401", 
                    description = "Unauthorized",
                    content = {@Content(schema = @Schema(implementation = MyAuthenticationEntryPoint.class)) }),
        })
    @PostMapping("/rentals")
    public ResponseEntity<Object> saveRental(
            @ModelAttribute RequestRentals rental) {
        // **File */
        Attachment attachment = attachmentService.saveAttachment(rental.getPicture());
        Rental newRental = rentalService.saveRental(new Rental(
            rental.getName(), rental.getSurface(), attachment.getAttachmentUrl(), rental.getPrice(),
            rental.getDescription(), rental.getOwner_id(), attachment
            ));

        return ResponseRentals.generateResponseMessage(HttpStatus.OK, newRental, "Item was successfully saved");
    }

    // !UPDATE
    @Operation(
        summary = "Update {Rental} by Id", 
        description = "Update a {Rental} object by specifying its id. The response is {Rental} object.", 
        tags = {"{Rental}", "put" })
        @ApiResponses({
                @ApiResponse(
                    responseCode = "200", 
                    description = "The updated {Rental} matching this Id was successfully modified",
                    content = {@Content(schema = @Schema(implementation = Rental.class), mediaType = "application/json") }),
                @ApiResponse(
                    responseCode = "404", 
                    description = "{Rental} Not Found", 
                    content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}),

                @ApiResponse(
                    responseCode = "401", 
                    description = "Unauthorized",
                    content = {@Content(schema = @Schema(implementation = MyAuthenticationEntryPoint.class)) }),
        })
    @PutMapping("/rentals/{id}")
    public ResponseEntity<Object>  updateRental(
            @PathVariable("id") Integer id,
            @ModelAttribute @Validated RequestRentals rental) {

        Attachment attachment = attachmentService.getAttachmentById(id);
       
        Rental updateRental = rentalService.updateRental(new Rental(
            id, rental.getName(), rental.getSurface(), attachment.getAttachmentUrl(), rental.getPrice(),
            rental.getDescription(), rental.getOwner_id(), rental.getCreated_at(), new Date(), attachment
            ));

        return ResponseRentals.generateResponseMessage(HttpStatus.OK, updateRental, "Item was successfully saved");

    }

    // // !DELETE
    // @DeleteMapping("/rentals/{id}")
    // public ResponseEntity<?> deleteRental(@PathVariable("id") Integer id) {
    //     rentalService.deleteRental(id);
    //     return (ResponseEntity<?>) ResponseEntity.noContent();
    // }

   

   

}
