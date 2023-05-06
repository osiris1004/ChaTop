package com.backend.chatop.controller.Mail;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestMail {
    private String message;
    private Integer user_id;
    private Integer rental_id;

}
