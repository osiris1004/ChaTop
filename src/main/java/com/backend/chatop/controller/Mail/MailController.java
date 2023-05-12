package com.backend.chatop.controller.Mail;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.chatop.errors.GlobalExceptionHandler.MyAuthenticationEntryPoint.MyAuthenticationEntryPoint;
import com.backend.chatop.model.Rental;
import com.backend.chatop.service.Email.EmailService;
import com.backend.chatop.service.User.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

@Tag(name = "Mail", description = "Mail management APIs")
public class MailController {

    private final EmailService emailService;
    private final UserService userService;


    @Operation(
        summary = "Send a {Mail}", 
        description = "send a {Mail}. The response is a {mail} object message.", 
        tags = {"{Mail}", "post" })
        @ApiResponses({
                @ApiResponse(
                    responseCode = "200", 
                    description = "The {Mail} was successfully send ",
                    content = {@Content(schema = @Schema(implementation = ResponseMail.class), mediaType = "application/json") }),
             
                @ApiResponse(
                    responseCode = "401", 
                    description = "Unauthorized",
                    content = {@Content(schema = @Schema(implementation = MyAuthenticationEntryPoint.class)) }),
                    
        })
    @PostMapping("/messages")
    public ResponseMail sendMail (
        @RequestBody RequestMail mail
    ){
        //address_to
        // String addres_from = userService.getUserById(mail.getUser_id()).getEmail();
        // String address_to = userService.getUserById(mail.getRental_id()).getEmail();
        
        return new ResponseMail( emailService.sendConfirmationEmail(mail.getMessage(), "ray2018095@gmail.com"));
    }
}
