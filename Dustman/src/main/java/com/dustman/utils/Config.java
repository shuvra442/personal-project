package com.dustman.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class Config {
    private final GlobalDataStore globalDataStore;

    public Config(GlobalDataStore globalDataStore) {
        this.globalDataStore = globalDataStore;
    }

    @Bean
    @DependsOn("globalDataStore")
    public JavaMailSender mailSender() {
        String emailPassword = globalDataStore.passwordMap.get("EmailPassword");
        String emailID = globalDataStore.passwordMap.get("EmailID");

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.zoho.in");
        mailSender.setPort(465);

        mailSender.setUsername(emailID);
        mailSender.setPassword(emailPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.transport.protocol", "smtp");

        return mailSender;
    }
}
