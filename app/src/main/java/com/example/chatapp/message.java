package com.example.chatapp;

public class message {

    private String message_type;
    private boolean me_or_him;
    private String message_text;
    private int his_profile_img;
    private int message_photo;
    private int voice_time;
    private boolean  voice_message_running;
    private long baseoffset;


    public message(String message_type, String message_text ) {
        this.message_type = message_type;
        this.message_text = message_text;
        me_or_him = true;
    }

    public message(String message_type, String message_text , int his_profile_img) {
        this.message_type = message_type;
        this.message_text = message_text;
        this.his_profile_img = his_profile_img;
        me_or_him=false;
    }

//    public message(String message_type, boolean me_or_him, int message_photo , int his_profile_img) {
//        this.message_type = message_type;
//        this.me_or_him = me_or_him;
//        this.message_photo = message_photo;
//        this.his_profile_img = his_profile_img;
//
//    }

    public message(String message_type, int voice_time, long baseoffset) {
        this.message_type = message_type;
        this.voice_time = voice_time*1000;
        this.voice_message_running =false;
        this.baseoffset = baseoffset;
        me_or_him = true;
    }
    public message(String message_type, int voice_time, long baseoffset , int his_profile_img) {
        this.message_type = message_type;
        this.voice_time = voice_time*1000;
        this.voice_message_running =false;
        this.baseoffset = baseoffset;
        this.his_profile_img = his_profile_img;
        me_or_him = false;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public boolean isMe_or_him() {
        return me_or_him;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }
//
//    public int getMessage_photo() {
//        return message_photo;
//    }
//
//    public void setMessage_photo(int message_photo) {
//        this.message_photo = message_photo;
//    }

    public int getVoice_time() {
        return voice_time;
    }

    public void setVoice_time(int voice_time) {
        this.voice_time = voice_time;
    }

    public boolean isVoice_message_running() {
        return voice_message_running;
    }

    public void setVoice_message_running(boolean voice_message_running) {
        this.voice_message_running = voice_message_running;
    }

    public long getBaseoffset() {
        return baseoffset;
    }

    public void setBaseoffset(long baseoffset) {
        this.baseoffset = baseoffset;
    }

    public int getHis_profile_img() {
        return his_profile_img;
    }

    public void setHis_profile_img(int his_profile_img) {
        this.his_profile_img = his_profile_img;
    }
}
