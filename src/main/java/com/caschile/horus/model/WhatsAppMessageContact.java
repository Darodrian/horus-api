package com.caschile.horus.model;

public class WhatsAppMessageContact {

    private WhatsAppMessageProfile profile;
    private String wa_id;

    public WhatsAppMessageProfile getProfile() {
        return profile;
    }

    public void setProfile(WhatsAppMessageProfile profile) {
        this.profile = profile;
    }

    public String getWa_id() {
        return wa_id;
    }

    public void setWa_id(String wa_id) {
        this.wa_id = wa_id;
    }
}
