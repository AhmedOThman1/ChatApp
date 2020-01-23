package com.example.chatapp;

public class message {
    private String message_text ;
    int message_photo , voice_time;
    boolean meORyou , voiceORtext  , voice_message_running;
    long baseoffset;


    message(String message_text , boolean meORyou , int message_photo , boolean voiceORtext , int voice_time)
    {
        this.meORyou = meORyou;
        this.message_text = message_text;
        this.message_photo = message_photo;
        this.voiceORtext = voiceORtext;
        this.voice_time = voice_time*1000;
        voice_message_running =false;
        baseoffset = 0;
    }

    public long getBaseoffset() {
        return baseoffset;
    }

    public void setBaseoffset(long baseoffset) {
        this.baseoffset = baseoffset;
    }

    public void setVoice_message_running(boolean voice_message_running) {
        this.voice_message_running = voice_message_running;
    }

    public boolean getVoice_message_running() {
        return voice_message_running;
    }

    public void setvoice_time(int his_voice_time) {
        this.voice_time = his_voice_time;
    }

    public int getvoice_time() {
        return voice_time;
    }


    public int getMessage_photo() {
        return message_photo;
    }

    public boolean isMeORyou() {
        return meORyou;
    }

    public boolean isVoiceORtext() {
        return voiceORtext;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMeORyou(boolean meORyou) {
        this.meORyou = meORyou;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public void setMessage_photo(int message_photo) {
        this.message_photo = message_photo;
    }

    public void setVoiceORtext(boolean voiceORtext) {
        this.voiceORtext = voiceORtext;
    }
}
