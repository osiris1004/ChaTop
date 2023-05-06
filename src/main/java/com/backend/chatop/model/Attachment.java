package com.backend.chatop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "attachment")
@Table(name = "attachment")

public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String type;
    private String  attachmentUrl;

    @Lob
    @Column(length=100000)
    private byte[] data;

    // // ***************************** */
    // @OneToOne(mappedBy = "attachment")
    
    // @JsonBackReference
    // private Rental rental;

    public Attachment(String fileName, String contentType, byte[] compressImage) {
        this.name = fileName;
        this.type = contentType;
        this.data = compressImage;
    }
    public Attachment(Integer id, String fileName, String contentType, byte[] compressImage, String url) {
        this.id = id;
        this.name = fileName;
        this.type = contentType;
        this.data = compressImage;
        this.attachmentUrl = url;
    }

}
