package com.caschile.horus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WhatsAppMessageValue {

    private String messaging_product;
    private WhatsAppMessageMetadata metadata;
    private ArrayList<WhatsAppMessageContact> contacts;
    private ArrayList<WhatsAppMessageMessage> messages;

    public String getMessaging_product() {
        return messaging_product;
    }

    public void setMessaging_product(String messaging_product) {
        this.messaging_product = messaging_product;
    }

    public WhatsAppMessageMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(WhatsAppMessageMetadata metadata) {
        this.metadata = metadata;
    }

    public ArrayList<WhatsAppMessageContact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<WhatsAppMessageContact> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<WhatsAppMessageMessage> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<WhatsAppMessageMessage> messages) {
        this.messages = messages;
    }
}
