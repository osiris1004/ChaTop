package com.backend.chatop.model;
import java.util.Date;
import java.util.HashMap;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;




@Data   

@NoArgsConstructor 
@AllArgsConstructor
@Entity(name = "rental") 
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private  Integer id;

    @Column(name = "name")
    private String name;


    @Column(name = "surface")
    private  Double surface;


    @Column(name = "picture")
    private String picture;

    @Column(name = "price")
    private  Double  price;
    

    @Column(name = "description")
    private String description;

    
    @Column(name = "owner_id")
    private Integer owner_id;

    @CreationTimestamp
    @Column(name = "created_at",  nullable = false, updatable = false)
    private Date created_at;


//***************************** */
    //foreign key
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    
    @JsonManagedReference
    private Attachment attachment;
  
   

    
}
