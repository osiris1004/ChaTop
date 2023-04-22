package com.backend.chatop.model.Rental;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import lombok.*;




@Data   
@Builder
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

    @Id
    @Column(name = "surface")
    private  Double surface;

    @Id
    @Column(name = "price")
    private  Double  price;
    
    @Column(name = "picture")
    private byte[] picture;

    @Column(name = "description")
    private String description;

    @Column(name = "owner_id")
    private Integer owner_id;

    @CreationTimestamp
    @Column(name = "created_at",  nullable = false, updatable = false)
    private Date created_at;

    @CreationTimestamp
    @Column(name = "updated_at",  updatable = true)
    private Date updated_at;

  

    
}
