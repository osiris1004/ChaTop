package com.backend.chatop.controller.Rentals;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import com.backend.chatop.model.Attachment;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRentals {
    private  Integer id;
    private String name;
    private  Double surface;
    private MultipartFile picture;
    private  Double  price;
    private String description;
    private Integer owner_id;
    private Attachment attachment;
    private Date created_at;

   
}
