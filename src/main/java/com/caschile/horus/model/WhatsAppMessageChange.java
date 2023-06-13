package com.caschile.horus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WhatsAppMessageChange {

    private WhatsAppMessageValue value;

    public WhatsAppMessageValue getValue() {
        return value;
    }

    public void setValue(WhatsAppMessageValue value) {
        this.value = value;
    }
}
