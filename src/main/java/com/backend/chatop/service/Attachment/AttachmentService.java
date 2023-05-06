package com.backend.chatop.service.Attachment;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backend.chatop.FileUtils.ImageUtils;
import com.backend.chatop.errors.GlobalExceptionHandler.ResourceNotFoundException;
import com.backend.chatop.model.Attachment;
import com.backend.chatop.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AttachmentService implements IAttachmentService{

    private final AttachmentRepository attachmentRepository;



    @Override
    public byte[] getImageByAttachmentId(Integer id) {
        Optional<Attachment> attachment = attachmentRepository.findById(id);
        if(attachment.isPresent()){
            return attachment.get().getData();
        }
        throw  new ResourceNotFoundException("Not found Attachments with id = " + id);
    }

    @Override
    public Attachment getAttachmentById(Integer id)  {
        Optional<Attachment> attachment = attachmentRepository.findById(id);
        if(attachment.isPresent()){
            return attachment.get();
        }
        throw  new ResourceNotFoundException("Not found Attachments with id = " + id);
    }

    @Override
    public Attachment saveAttachment(MultipartFile file) throws RuntimeException{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {

            Attachment attachment =  attachmentRepository.save(new Attachment(fileName, file.getContentType(), file.getBytes()));
            String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/attachment/")
                .path(String.valueOf(attachment.getId()))
                .toUriString();

                attachment.setAttachmentUrl(fileDownloadUri);
            return attachment;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Attachment updateAttachment(MultipartFile file, Integer id) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/attachment/")
                .path(String.valueOf(id))
                .toUriString();
            return attachmentRepository.save(new Attachment(id, fileName, file.getContentType(), file.getBytes(), fileDownloadUri ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    

    @Override
    public void deleteAttachment(Integer id) {
        attachmentRepository.deleteById(id);
    }
    
}
