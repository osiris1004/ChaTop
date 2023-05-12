package com.backend.chatop.service.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class EmailService {
    private final static String EMAIL_CONFIRMATION_SUBJECT = "Rental Message P3";

    @Autowired
    private JavaMailSender javaMailSender;

    public String sendConfirmationEmail(String token, String email) {
        // build email
        // send message
      try {
        String message = token;
        String from = "no-reply@udeesa.org";
        send(email, from, message);
        return "Your email was successfully send";
      } catch (Exception e) {
        return "Something went wrong, contact the administration ";
      }
    }

    @Async
    private void send(String to, String from, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(EMAIL_CONFIRMATION_SUBJECT);
            helper.setText(email);
          
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("failed to send email");
        }
    }
}
