package com.store.service.impl;

import com.store.entity.Item;
import com.store.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String username;

    @Override
    public void sendMail(String mailTo, List<Item> itemList) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(mailTo);
        mailMessage.setSubject("OnlineStore Avizhen");
        mailMessage.setText(String.valueOf(itemList));
        mailSender.send(mailMessage);
    }
}
