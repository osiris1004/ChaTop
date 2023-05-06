package com.backend.chatop.service.Attachment;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.backend.chatop.model.Attachment;

public interface IAttachmentService {
    byte[] getImageByAttachmentId(Integer id);
    Attachment getAttachmentById(Integer id);
    Attachment saveAttachment(MultipartFile file);
    Attachment updateAttachment(MultipartFile file, Integer id);
    void deleteAttachment(Integer id);
}
