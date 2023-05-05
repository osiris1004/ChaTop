package com.backend.chatop.controller.Attachement;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.chatop.errors.GlobalExceptionHandler.ErrorMessage;
import com.backend.chatop.model.Attachment;
import com.backend.chatop.model.Rental;
import com.backend.chatop.service.Attachment.AttachmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")

@Tag(name = "Attachment", description = "Attachment management APIs")
public class AttachmentController {

    private final AttachmentService attachmentService;
    
     // !GET_PICTURE
     @Operation(
        summary = "fetch {image} by id", 
        description = "get an {image} file by specifying its id. The response is an {image} file.", 
        tags = {"{Image}", "get" })
        @ApiResponses({
                @ApiResponse(
                    responseCode = "200", 
                    description = "Found the {Image} matching this Id",
                    content = {@Content(schema = @Schema(implementation = Attachment.class), mediaType = "image/png") }),
                @ApiResponse(
                    responseCode = "404", 
                    description = "{Image} Not Found", 
                    content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}),
        })
    @GetMapping("/attachment/{id}")
    public ResponseEntity<?> getImageByName(
        @PathVariable("id") Integer id) {
        byte[] image = attachmentService.getImageByAttachmentId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
}
