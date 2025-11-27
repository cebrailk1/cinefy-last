package org.example.cinefylast.controller;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@RestController

public class MailController {
    public JavaMailSender mailSender;
    @Autowired
    public MailController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RequestMapping("/send-email")
    public String sendEmail(){
        SimpleMailMessage message = new SimpleMailMessage();
        try {
            message.setFrom("cebrail.kocakafa@gmail.com");
            message.setTo("cebrail.kocakafa@gmail.com");
            message.setSubject("Test Email");
            message.setText("This is a test email");

            mailSender.send(message);
            return "Email Sent";
        } catch (Exception e) {
            return e.getMessage();    }
    }

    @RequestMapping("/send-email-with-attachment")
    public String sendMailWithAttachment(){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("cebrail.kocakafa@gmail.com");
            helper.setTo("cebrail.kocakafa@gmail.com");
            helper.setSubject("Test Email with attachment");
            helper.setText("This is a test email mit attachment!");
            helper.addAttachment("pic.png", new File("/home/cebrail/Documents/Projects/cinefy/mail-sender/src/main/resources/pic.png"));
            mailSender.send(message);
            return ("Email Sent");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    @RequestMapping("/send-email-with-html")
    public String sendMailWithHtml(){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("cebrail.kocakafa@gmail.com");
            helper.setTo("cebrail.kocakafa@gmail.com");
            helper.setSubject("Test Email with HTML");
            try (var inputStream = Objects.requireNonNull(MailController.class.getResourceAsStream("/templates/mail-content.html"))  ) {
                helper.setText(
                        new String(inputStream.readAllBytes(), StandardCharsets.UTF_8), true
                );
            } catch (Exception e) {
                return  e.getMessage();            }
            helper.addAttachment("pic.png", new File("/home/cebrail/Documents/Projects/cinefy/mail-sender/src/main/resources/pic.png"));
            mailSender.send(message);
            return ("Email Sent");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
