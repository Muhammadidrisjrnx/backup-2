package com.example.rplrus021.bersama;

import java.util.Date;

public class chatting_model2 {
    private String text_chat;
    private String user;
    private long message_time;

    public chatting_model2(String text_chat,String user){
        this.setText_chat(text_chat);
        this.setUser(user);

        message_time = new Date().getTime();
    }
    public chatting_model2(){

    }

    public String getText_chat() {
        return text_chat;
    }

    public void setText_chat(String text_chat) {
        this.text_chat = text_chat;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getMessage_time() {
        return message_time;
    }

    public void setMessage_time(long message_time) {
        this.message_time = message_time;
    }
}
