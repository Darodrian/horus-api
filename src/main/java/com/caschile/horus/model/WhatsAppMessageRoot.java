package com.caschile.horus.model;

import java.util.ArrayList;

public class WhatsAppMessageRoot {

    private String object;
    private ArrayList<WhatsAppMessageEntry> entry;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public ArrayList<WhatsAppMessageEntry> getEntry() {
        return entry;
    }

    public void setEntry(ArrayList<WhatsAppMessageEntry> entry) {
        this.entry = entry;
    }
}
