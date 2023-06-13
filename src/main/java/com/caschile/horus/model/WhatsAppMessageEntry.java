package com.caschile.horus.model;

import java.util.ArrayList;

public class WhatsAppMessageEntry {

    private String id;
    private ArrayList<WhatsAppMessageChange> changes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<WhatsAppMessageChange> getChanges() {
        return changes;
    }

    public void setChanges(ArrayList<WhatsAppMessageChange> changes) {
        this.changes = changes;
    }
}
