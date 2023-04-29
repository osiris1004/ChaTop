package com.backend.chatop.controller.Mail;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.chatop.model.User.User;
import com.backend.chatop.service.Email.EmailService;
import com.backend.chatop.service.User.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MailController {

    private final EmailService emailService;
    private final UserService userService;


    @PostMapping("/messages")
    public ResponseMail sendMail (
        @RequestBody RequestMail mail
    ){
        //address_to
        String addres_from = userService.getUserById(mail.getUser_id()).getEmail();
        String address_to = userService.getUserById(mail.getRental_id()).getEmail();
        
        return new ResponseMail( emailService.sendConfirmationEmail(null, "ray2018095@gmail.com"));
    }
}
