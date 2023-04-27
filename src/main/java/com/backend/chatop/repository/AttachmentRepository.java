package com.backend.chatop.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.chatop.model.Attachment;



@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer>{
    Optional<Attachment> findByName(String attachmentName);
}

