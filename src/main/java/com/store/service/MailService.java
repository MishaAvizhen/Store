package com.store.service;

public interface MailService {
    void sendMail(String mailTo, String emailText, String subject);
}
