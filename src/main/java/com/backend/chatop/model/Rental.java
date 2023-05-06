package com.backend.chatop.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

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
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surface")
    private Double surface;

    @Column(name = "picture")
    private String picture;

    @Column(name = "price")
    private Double price;

    @Column(name = "description", columnDefinition="LONGTEXT")
    private String description;

    @Column(name = "owner_id")
    private Integer owner_id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date created_at;

    @CreationTimestamp
    @Column(name = "updated_at", updatable = true)
    private Date updated_at;

    // foreign key
    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "attachment_id", referencedColumnName = "id")

    // @JsonManagedReference
    // private Attachment attachment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;

    public Rental(String name, Double surface, String picture, Double double1, String string, Integer owner_id, Attachment attachment) {
        this.name = name;
        this.surface = surface;
        this.picture = picture;
        this.price = double1;
        this.description = string;
        this.owner_id = owner_id;
        this.attachment = attachment;
    }

 

   

}
