package com.store.service;

import com.store.entity.Item;

import java.util.List;

public interface MailService {
     void sendMail(String mailTo, List<Item> itemList);
}
