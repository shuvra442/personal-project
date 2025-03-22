package com.dustman.service;

import com.dustman.utils.GlobalDataStore;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final GlobalDataStore globalDataStore;


    public EmailService(JavaMailSender mailSender, GlobalDataStore globalDataStore) {
        this.mailSender = mailSender;
        this.globalDataStore = globalDataStore;
    }

    @Async
    public void sendEmail(String to, String subject, String text) {

        String emailID = globalDataStore.passwordMap.get("EmailID");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailID);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        try {
            mailSender.send(message);
            System.out.println("Mail Send ");
        } catch (Exception e) {
            System.out.println("Error sending email: " + e.getMessage());
        }
    }
}